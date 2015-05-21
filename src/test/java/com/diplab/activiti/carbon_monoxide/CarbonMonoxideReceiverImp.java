package com.diplab.activiti.carbon_monoxide;

public class CarbonMonoxideReceiverImp implements CarbonMonoxideReceiver {

	private String id = "Co-01";

	public CarbonMonoxideReceiverImp() {
	}

	public CarbonMonoxideReceiverImp(String id) {
		this.id = id;
	}

	public CarbonMonoxide getCo() {
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		CarbonMonoxide co = new CarbonMonoxide();
		co.setCo(Math.random() * 180 + 20);
		return co;
	}

	@Override
	public String getId() {
		return id;
	}
}
