package com.diplab.activiti.temperature;

import java.io.Serializable;
import java.util.Date;

public class Temperature implements Serializable {

	private static final long serialVersionUID = -349485433882229849L;
	private double temperature;

	public double getTemperature() {
		return temperature;
	}

	public void setTemperature(double temperature) {
		this.temperature = temperature;
	}

//	public Date getTime() {
//		return time;
//	}

//	public void setTime(Date time) {
//		this.time = time;
//	}

	private Date time;

	@Override
	public String toString() {
		return String.format("%f", temperature);
	}
}
