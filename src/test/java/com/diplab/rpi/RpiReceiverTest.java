package com.diplab.rpi;

import com.diplab.activiti.smoke.RpiSmokeReceiver;
import com.diplab.activiti.temperature.RpiTemperatureReceiver;

public class RpiReceiverTest {

	public static void main(String[] args) {
		RpiSmokeReceiver receiver = new RpiSmokeReceiver();
		System.out.println(String.format("[%s] Receive in C, smoke: %f ppm",
				RpiReceiverTest.class.getSimpleName(), receiver.getSmoke()
						.getSmoke()));

		RpiTemperatureReceiver tempReceiver = new RpiTemperatureReceiver();
		System.out.println(String.format("[%s] Receive in C, temp: %f Celsius",
				RpiReceiverTest.class.getSimpleName(), tempReceiver
						.getTemperature().getTemperature()));
	}

}
