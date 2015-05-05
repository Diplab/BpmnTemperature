package com.diplab.activiti.smoke;

import static org.junit.Assert.*;

import org.junit.Test;

public class RpiSmokeReceiverTest {

	@Test
	public void test() {
		RpiSmokeReceiver receiver = new RpiSmokeReceiver();
		assertTrue("bigger than 0", receiver.getSmoke().getSmoke() > 0);
	}

	public static void main(String[] args) {
		RpiSmokeReceiver receiver = new RpiSmokeReceiver();
		System.out.println(String.format("[%s] Receive in C, smoke: %f ppm",
				RpiSmokeReceiverTest.class.getSimpleName(), receiver.getSmoke()
						.getSmoke()));
	}

}
