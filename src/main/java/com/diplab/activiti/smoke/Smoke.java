package com.diplab.activiti.smoke;

public class Smoke {

	private double smoke;

	public double getSmoke() {
		return smoke;
	}

	public void setSmoke(double smoke) {
		this.smoke = smoke;
	}

	@Override
	public String toString() {
		return String.format("S=%f", smoke);
	}
}
