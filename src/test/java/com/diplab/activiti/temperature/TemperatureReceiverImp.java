package com.diplab.activiti.temperature;

import java.util.Date;

public class TemperatureReceiverImp implements TemperatureReceiver {

	public Temperature getTemperature() {
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		Temperature temperature = new Temperature();
		temperature.setTemperature(Math.random() * 50);
		//temperature.setTime(new Date());
		return temperature;
	}
}
