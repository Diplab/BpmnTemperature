package com.diplab.activiti.bpmn.converter.child;

import javax.xml.stream.XMLStreamReader;

import org.activiti.bpmn.converter.child.BaseChildElementParser;
import org.activiti.bpmn.model.BaseElement;
import org.activiti.bpmn.model.BpmnModel;

import com.diplab.activiti.Constant;
import com.diplab.activiti.bpmn.model.TemperatureEventDefinition;

public class IdParser extends BaseChildElementParser {

	@Override
	public String getElementName() {
		return Constant.ATTRIBUTE_ID;
	}

	@Override
	public void parseChildElement(XMLStreamReader xtr,
			BaseElement parentElement, BpmnModel model) throws Exception {

		if (parentElement instanceof TemperatureEventDefinition == false)
			return;

		TemperatureEventDefinition eventDefinition = (TemperatureEventDefinition) parentElement;
		eventDefinition.setId(xtr.getElementText());

	}

}
