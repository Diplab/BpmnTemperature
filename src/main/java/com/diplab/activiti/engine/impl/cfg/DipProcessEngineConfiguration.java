package com.diplab.activiti.engine.impl.cfg;

import java.util.Arrays;

import org.activiti.bpmn.converter.BpmnXMLConverter;
import org.activiti.engine.impl.cfg.StandaloneProcessEngineConfiguration;
import org.activiti.engine.parse.BpmnParseHandler;

import com.diplab.activiti.bpmn.converter.DiplabStartEventXMLConverter;
import com.diplab.activiti.engine.impl.bpmn.parser.handler.DiplabEventDefinitionParserHandler;
import com.diplab.activiti.engine.impl.bpmn.parser.handler.DiplabStartEventParserHandler;

public class DipProcessEngineConfiguration extends
		StandaloneProcessEngineConfiguration {

	static {
		BpmnXMLConverter.addConverter(new DiplabStartEventXMLConverter());
	}


	public DipProcessEngineConfiguration() {
		this.setCustomDefaultBpmnParseHandlers(Arrays
				.<BpmnParseHandler> asList(new DiplabStartEventParserHandler()));

		this.setPostBpmnParseHandlers(Arrays
				.<BpmnParseHandler> asList(new DiplabEventDefinitionParserHandler()));

	}

}
