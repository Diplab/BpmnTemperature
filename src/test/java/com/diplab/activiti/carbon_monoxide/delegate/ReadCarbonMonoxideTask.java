package com.diplab.activiti.carbon_monoxide.delegate;

import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.delegate.Expression;
import org.activiti.engine.delegate.JavaDelegate;

import com.diplab.activiti.carbon_monoxide.CarbonMonoxide;
import com.diplab.activiti.carbon_monoxide.CarbonMonoxideReceiver;


public class ReadCarbonMonoxideTask implements JavaDelegate {

	private Expression id;

	@Override
	public void execute(DelegateExecution execution) throws Exception {
		String idString = (String) id.getValue(execution);
		CarbonMonoxideReceiver co = CarbonMonoxideReceiver.getReceiverById(idString);
		System.out.println(ReadCarbonMonoxideTask.class.getName());
		CarbonMonoxide ppm = co.getCo();
		System.out.println(ppm);
		// System.out.println(execution.getVariable("lastestRecord"));
		execution.setVariable("ppm", ppm.getCo());
	}

}
