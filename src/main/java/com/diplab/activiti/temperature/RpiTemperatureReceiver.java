package com.diplab.activiti.temperature;

import java.util.Date;

public class RpiTemperatureReceiver implements TemperatureReceiver{

	public static native double getTemperatureCelsius();

	static {
		System.loadLibrary("Rpi");
	}
	
	@Override
	public Temperature getTemperature() {
		Temperature temp = new Temperature();
		temp.setTemperature(getTemperatureCelsius());
		temp.setTime(new Date());
		return temp;
	}

}
