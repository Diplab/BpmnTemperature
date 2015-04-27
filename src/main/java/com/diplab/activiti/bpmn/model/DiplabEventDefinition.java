package com.diplab.activiti.bpmn.model;

import org.activiti.bpmn.model.EventDefinition;

public class DiplabEventDefinition extends EventDefinition {

	protected String mode;
	protected String condition;
	protected String time = "10";// defautl : 10s

	public String getMode() {
		return mode;
	}

	public void setMode(String mode) {
		this.mode = mode;
	}

	public String getCondition() {
		return condition;
	}

	public void setCondition(String condition) {
		this.condition = condition;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	@Override
	public EventDefinition clone() {
		DiplabEventDefinition clone = new DiplabEventDefinition();
		clone.setValues(this);
		return null;
	}

	public void setValues(DiplabEventDefinition otherDefinition) {
		super.setValues(otherDefinition);
		setMode(otherDefinition.getMode());
		setCondition(otherDefinition.getCondition());
		setTime(otherDefinition.getTime());

	}

}
