package com.diplab.activiti.engine.impl.jobexecutor;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import org.activiti.engine.ActivitiException;
import org.activiti.engine.delegate.event.ActivitiEventType;
import org.activiti.engine.delegate.event.impl.ActivitiEventBuilder;
import org.activiti.engine.impl.cmd.StartProcessInstanceCmd;
import org.activiti.engine.impl.context.Context;
import org.activiti.engine.impl.interceptor.CommandContext;
import org.activiti.engine.impl.jobexecutor.JobHandler;
import org.activiti.engine.impl.persistence.deploy.DeploymentManager;
import org.activiti.engine.impl.persistence.entity.ByteArrayRef;
import org.activiti.engine.impl.persistence.entity.ExecutionEntity;
import org.activiti.engine.impl.persistence.entity.JobEntity;
import org.activiti.engine.repository.ProcessDefinition;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.SerializationUtils;

import com.diplab.activiti.engine.impl.persistence.entity.TemperatureEntity;
import com.diplab.activiti.temperature.Temperature;
import com.diplab.activiti.temperature.TemperatureReceiver;

public class TemperatureStartEventJobHandler implements JobHandler {
	private Logger logger = LoggerFactory
			.getLogger(TemperatureStartEventJobHandler.class);

	public static final String TYPE = "temperature-start-event";

	@Override
	public String getType() {
		return TYPE;
	}

	@Override
	public void execute(JobEntity job, String configuration,
			ExecutionEntity execution, CommandContext commandContext) {
		ByteArrayRef ref = new ByteArrayRef(configuration);
		TemperatureEntity entity = (TemperatureEntity) SerializationUtils
				.deserialize(ref.getBytes());

		TemperatureReceiver receiver = TemperatureReceiver
				.getReceiverByDeviceId(entity.getSensor_id());
		if (receiver == null) {
			throw new ActivitiException(String.format(
					"%s sensor is not presenting yet", entity.getSensor_id()));
		}
		Temperature temperature = receiver.getTemperature();
		logger.info(String.format("get Temperature = %f from %s",
				temperature.getTemperature(), entity.getSensor_id()));

		DeploymentManager deploymentCache = Context
				.getProcessEngineConfiguration().getDeploymentManager();

		ProcessDefinition processDefinition = null;
		processDefinition = deploymentCache
				.findDeployedProcessDefinitionById(job.getProcessDefinitionId());

		if (processDefinition == null) {
			throw new ActivitiException(
					"Could not find process definition needed for temperature start event");
		}

		if (!processDefinition.isSuspended()) {
			if (commandContext.getEventDispatcher().isEnabled()) {
				commandContext.getEventDispatcher().dispatchEvent(
						ActivitiEventBuilder.createEntityEvent(
								ActivitiEventType.TIMER_FIRED, job));
			}

			Map<String, Object> variables = new HashMap<String, Object>();
			variables.put("temperature", Arrays.<Temperature>asList(receiver.getTemperature()));
			new StartProcessInstanceCmd<Object>(null, processDefinition.getId(), null, variables ).execute(commandContext);
		}
	}
}
