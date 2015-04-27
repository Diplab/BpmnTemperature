package com.diplab.activiti.smoke;

public class SmokeReceiverImp implements SmokeReceiver {

	public Smoke getSmoke() {
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		Smoke smoke = new Smoke();
		smoke.setSmoke(Math.random() * 180 + 20);
		return smoke;
	}
}
