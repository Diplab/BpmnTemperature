package com.diplab.activiti.smoke.delegate;

import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.delegate.JavaDelegate;

import com.diplab.activiti.smoke.Smoke;
import com.diplab.activiti.smoke.SmokeReceiverImp;

public class ReadSmokeTask implements JavaDelegate {

	@Override
	public void execute(DelegateExecution execution) throws Exception {
		SmokeReceiverImp smoke = new SmokeReceiverImp();
		System.out.println(ReadSmokeTask.class.getName());
		Smoke ppm = smoke.getSmoke();
		System.out.println(ppm);
		//System.out.println(execution.getVariable("lastestRecord"));
		execution.setVariable("ppm", Double.parseDouble(ppm.toString()));
	}

}
