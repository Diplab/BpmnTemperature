package com.diplab.activiti.carbon_monoxide;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public interface CarbonMonoxideReceiver {
	public CarbonMonoxide getCo();

	public String getId();

	static public List<CarbonMonoxideReceiver> receivers = new ArrayList<CarbonMonoxideReceiver>();

	static public void addReceiver(CarbonMonoxideReceiver receiver) {
		receivers.add(receiver);
	}

	static public CarbonMonoxideReceiver getReceiverById(String id) {
		CarbonMonoxideReceiver receiver = null;
		for (Iterator<CarbonMonoxideReceiver> iterator = receivers.iterator(); iterator
				.hasNext();) {
			CarbonMonoxideReceiver carbonMonoxideReceiver = iterator.next();
			if (carbonMonoxideReceiver.getId().equalsIgnoreCase(id)) {
				receiver = carbonMonoxideReceiver;
			}
		}

		return receiver;
	}
}
