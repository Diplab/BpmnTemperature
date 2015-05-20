package com.diplab.activiti.smoke;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public interface SmokeReceiver {
	public Smoke getSmoke();

	public String getId();

	static public List<SmokeReceiver> receivers = new ArrayList<SmokeReceiver>();

	static public void addReceiver(SmokeReceiver receiver) {
		receivers.add(receiver);
	}

	static public SmokeReceiver getReceiverById(String id) {
		SmokeReceiver receiver = null;
		for (Iterator<SmokeReceiver> iterator = receivers.iterator(); iterator
				.hasNext();) {
			SmokeReceiver smokeReceiver = iterator.next();
			if (smokeReceiver.getId().equalsIgnoreCase(id)) {
				receiver = smokeReceiver;
			}
		}

		return receiver;
	}
}
