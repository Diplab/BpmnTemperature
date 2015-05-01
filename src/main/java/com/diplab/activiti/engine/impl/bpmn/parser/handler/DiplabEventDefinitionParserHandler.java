package com.diplab.activiti.engine.impl.bpmn.parser.handler;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.activiti.bpmn.model.BaseElement;
import org.activiti.engine.ProcessEngines;
import org.activiti.engine.impl.bpmn.parser.BpmnParse;
import org.activiti.engine.impl.bpmn.parser.handler.AbstractBpmnParseHandler;
import org.activiti.engine.impl.persistence.entity.ProcessDefinitionEntity;
import org.activiti.engine.impl.pvm.delegate.ActivityBehavior;
import org.activiti.engine.impl.pvm.delegate.ActivityExecution;
import org.activiti.engine.impl.pvm.process.ActivityImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.diplab.activiti.bpmn.model.DiplabEventDefinition;
import com.diplab.activiti.engine.impl.jobexecutor.TemperatureDeclarationImpl;
import com.diplab.activiti.engine.impl.jobexecutor.TemperatureDeclarationType;
import com.diplab.activiti.temperature.IsSatisfy;
import com.diplab.activiti.temperature.Temperature;
import com.diplab.activiti.temperature.TemperatureEventListener;

public class DiplabEventDefinitionParserHandler extends
		AbstractBpmnParseHandler<DiplabEventDefinition> {

	private static final Logger logger = LoggerFactory
			.getLogger(DiplabEventDefinitionParserHandler.class);

	public static List<TemperatureEventListener> listeners = Collections
			.synchronizedList(new ArrayList<TemperatureEventListener>());
	@Override
	protected Class<? extends BaseElement> getHandledType() {
		return DiplabEventDefinition.class;
	}

	@Override
	protected void executeParse(BpmnParse bpmnParse,
			DiplabEventDefinition eventDefinition) {

		/*
		 * 1. prepare activity behavior 2. prepare TemperatureEventListener 3.
		 * add TemperatureEventListener into scheduler
		 */

		// 1. Behavior: go through next activity
		ActivityImpl temperatureEventActivity = bpmnParse.getCurrentActivity();
		temperatureEventActivity.setActivityBehavior(new ActivityBehavior() {
			private static final long serialVersionUID = 1L;

			@Override
			/**
			 * Just go through the next activity
			 * Only one transition should be taken
			 */
			public void execute(ActivityExecution execution) throws Exception {
				System.out.println("Temperature");
				execution.take(execution.getActivity().getOutgoingTransitions()
						.get(0));

			}
		});

		/*
		 * 2. prepare TemperatureEventListener
		 * 
		 * 2.1 Prepare TemperatureDeclarationType 2.2 Use
		 * TemperatureDeclarationType.prepareIsSatisfy() 2.3 New
		 * TemperatureEventListener with IsSatisfy and implement activate and
		 * isEnd
		 */

		String conditionString = eventDefinition.getCondition();
		String typeString = eventDefinition.getMode();
		String timeString = eventDefinition.getTime();

		TemperatureDeclarationType type;
		double condition = 0;
		int time = 1;

		if (conditionString != null) {
			condition = Double.parseDouble(eventDefinition.getCondition());
		}

		if (typeString == null) {
			type = TemperatureDeclarationType.NONE;
		} else if (typeString.equalsIgnoreCase("greater")) {
			type = TemperatureDeclarationType.GREATER;
		} else if (typeString.equalsIgnoreCase("lesser")) {
			type = TemperatureDeclarationType.LESSER;
		} else if (typeString.equalsIgnoreCase("avg_greater")) {
			type = TemperatureDeclarationType.AVG_GREATER;
		} else if (typeString.equalsIgnoreCase("avg_lesser")) {
			type = TemperatureDeclarationType.AVG_LESSER;
		} else {
			logger.warn(String.format("%s is not supportted", typeString));
			return;
		}

		if (timeString != null) {
			time = Integer.parseInt(timeString);
		}

		TemperatureDeclarationImpl declarationImpl = new TemperatureDeclarationImpl(
				type, condition, time);

		IsSatisfy isSatisfy = declarationImpl.prepareIsSatisfy();
		if (isSatisfy == null) {
			// isSatisfy is null -> it's impossible to be activated.
			return;
		}
		final ProcessDefinitionEntity processDefinition = bpmnParse
				.getCurrentProcessDefinition();

		TemperatureEventListener listener = new TemperatureEventListener(
				isSatisfy) {

			@Override
			public void activate(List<Temperature> records) {
				Map<String, Object> variables = new HashMap<String, Object>();
				variables.put("records", records);
				variables.put("lastestRecord", records.get(0));
				ProcessEngines
						.getDefaultProcessEngine()
						.getRuntimeService()
						.startProcessInstanceById(processDefinition.getId(),
								variables);
			}

			@Override
			public boolean isEnd() {
				return false;
			}
		};

		// 3. schedule
		listeners.add(listener);

	}
}
