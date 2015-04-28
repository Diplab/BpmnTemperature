package com.diplab.activiti.temperature.delegate;

import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.delegate.JavaDelegate;

import com.diplab.activiti.smoke.Smoke;
import com.diplab.activiti.smoke.SmokeReceiverImp;

public class ReadSmokeTask implements JavaDelegate {

	@Override
	public void execute(DelegateExecution execution) throws Exception {
		SmokeReceiverImp smoke = new SmokeReceiverImp();
		System.out.println(ReadSmokeTask.class.getName());
		System.out.println(smoke.getSmoke());
		Smoke ppm = smoke.getSmoke();
		execution.setVariable("ppm", ppm);
	}

}
