package com.diplab.activiti.smoke;

public class SmokeReceiverImp implements SmokeReceiver {

	private String id = "Smoke-s-01";

	public SmokeReceiverImp() {
	}

	public SmokeReceiverImp(String id) {
		this.id = id;
	}

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

	@Override
	public String getId() {
		return id;
	}
}
