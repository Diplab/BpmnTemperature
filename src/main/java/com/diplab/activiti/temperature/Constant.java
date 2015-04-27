package com.diplab.activiti.temperature;

import java.util.HashMap;
import java.util.Map;

import org.activiti.bpmn.converter.child.BaseChildElementParser;

import com.diplab.activiti.bpmn.converter.child.ConditionParser;
import com.diplab.activiti.bpmn.converter.child.DiplabEventDefinitionParser;
import com.diplab.activiti.bpmn.converter.child.ModeParser;
import com.diplab.activiti.bpmn.converter.child.TimeParser;

public class Constant {
	public static final String ATTRIBUTE_CONDITION = "condition";
	public static final String ELEMENT_DIPLAB_EVENT_DEFINITION = "diplabEventDefinition";
	public static final String ATTRIBUTE_MODE = "mode";
	public static final String ATTRIBUTE_TIME = "time";

	private Constant() {
	}

	public static Map<String, BaseChildElementParser> DIP_PARSER;

	static {
		DIP_PARSER = new HashMap<String, BaseChildElementParser>();
		BaseChildElementParser parser = new DiplabEventDefinitionParser();
		BaseChildElementParser modeparser = new ModeParser();
		BaseChildElementParser conditionparser = new ConditionParser();
		BaseChildElementParser timeParser = new TimeParser();

		DIP_PARSER.put(parser.getElementName(), parser);
		DIP_PARSER.put(modeparser.getElementName(), modeparser);
		DIP_PARSER.put(conditionparser.getElementName(), conditionparser);
		DIP_PARSER.put(timeParser.getElementName(), timeParser);
	}

}
