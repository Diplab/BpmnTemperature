package com.diplab.activiti.smoke;

public class RpiSmokeReceiver implements SmokeReceiver {

	public static native double getSmokePpm();

	static {
		System.loadLibrary("Rpi");
	}

	public Smoke getSmoke() {
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		Smoke smoke = new Smoke();
		smoke.setSmoke(getSmokePpm());
		return smoke;
	}

}
