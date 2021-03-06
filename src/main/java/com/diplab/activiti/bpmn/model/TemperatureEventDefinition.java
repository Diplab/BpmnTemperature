package com.diplab.activiti.bpmn.model;

import org.activiti.bpmn.model.EventDefinition;

public class TemperatureEventDefinition extends EventDefinition {

	protected String mode;
	protected String condition;
	protected String time = "10";// defautl : 10s
	protected String id; // indicate which device

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

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@Override
	public EventDefinition clone() {
		TemperatureEventDefinition clone = new TemperatureEventDefinition();
		clone.setValues(this);
		return null;
	}

	public void setValues(TemperatureEventDefinition otherDefinition) {
		super.setValues(otherDefinition);
		setMode(otherDefinition.getMode());
		setCondition(otherDefinition.getCondition());
		setTime(otherDefinition.getTime());
		setId(otherDefinition.getId());
	}

	@Override
	public String toString() {
		return "TemperatureEventDefinition [mode=" + mode + ", condition="
				+ condition + ", time=" + time + ", id=" + id + "]";
	}

}
