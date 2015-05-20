package com.diplab.activiti.smoke.delegate;

import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.delegate.Expression;
import org.activiti.engine.delegate.JavaDelegate;

import com.diplab.activiti.smoke.Smoke;
import com.diplab.activiti.smoke.SmokeReceiver;

public class ReadSmokeTask implements JavaDelegate {

	private Expression id;

	@Override
	public void execute(DelegateExecution execution) throws Exception {
		String idString = (String) id.getValue(execution);
		SmokeReceiver smoke = SmokeReceiver.getReceiverById(idString);
		System.out.println(ReadSmokeTask.class.getName());
		Smoke ppm = smoke.getSmoke();
		System.out.println(ppm);
		// System.out.println(execution.getVariable("lastestRecord"));
		execution.setVariable("ppm", ppm.getSmoke());
	}

}
