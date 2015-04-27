package com.diplab.activiti.temperature;

import java.util.Date;

public class Temperature {

	private double temperature;

	public double getTemperature() {
		return temperature;
	}

	public void setTemperature(double temperature) {
		this.temperature = temperature;
	}

	public Date getTime() {
		return time;
	}

	public void setTime(Date time) {
		this.time = time;
	}

	private Date time;

	@Override
	public String toString() {
		return String.format("%s T=%f", time.toString(), temperature);
	}
}
