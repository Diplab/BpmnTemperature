package com.diplab.activiti.carbon_monoxide;

import java.util.Date;

public class CarbonMonoxide {

	// ppm
	private double co;
	private Date time = new Date();

	public double getCo() {
		return co;
	}

	public void setCo(double co) {
		this.co = co;
	}

	public Date getTime() {
		return time;
	}

	public void setTime(Date time) {
		this.time = time;
	}

	@Override
	public String toString() {
		return String.format("%s C=%fppm", time.toString(), co);
	}
}
