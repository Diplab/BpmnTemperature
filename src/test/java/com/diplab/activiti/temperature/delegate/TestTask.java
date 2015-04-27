package com.diplab.activiti.temperature.delegate;

import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.delegate.JavaDelegate;

import com.diplab.activiti.smoke.Smoke;
import com.diplab.activiti.smoke.SmokeReceiverImp;

public class TestTask implements JavaDelegate {

	@Override
	public void execute(DelegateExecution execution) throws Exception {
		SmokeReceiverImp smoke = new SmokeReceiverImp();
		System.out.println(TestTask.class.getName());
		// System.out.println(smoke.getSmoke());
		Smoke ppm = smoke.getSmoke();
		System.out.println(ppm);

	}

}
