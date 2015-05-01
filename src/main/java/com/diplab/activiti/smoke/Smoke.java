package com.diplab.activiti.smoke;

import java.util.Date;

public class Smoke {

	// ppm
	private double smoke;
	private Date time = new Date();

	public double getSmoke() {
		return smoke;
	}

	public void setSmoke(double smoke) {
		this.smoke = smoke;
	}

	public Date getTime() {
		return time;
	}

	public void setTime(Date time) {
		this.time = time;
	}

	@Override
	public String toString() {
		return String.format("%s C=%fppm", time.toString(), smoke);
	}
}
