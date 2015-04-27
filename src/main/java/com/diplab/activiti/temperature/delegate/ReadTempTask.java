package com.diplab.activiti.temperature.delegate;

import org.activiti.engine.ProcessEngines;
import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.delegate.JavaDelegate;

import com.diplab.activiti.engine.impl.cfg.DipProcessEngineConfiguration;
import com.diplab.activiti.temperature.TemperatureReceiver;
import com.diplab.activiti.temperature.TemperatureReceiverImp;

public class ReadTempTask implements JavaDelegate {
	TemperatureReceiver receiver = new TemperatureReceiverImp();

	@Override
	public void execute(DelegateExecution execution) throws Exception {
		System.out.println(ReadTempTask.class.getName());
		while (true) {
			((DipProcessEngineConfiguration) ProcessEngines
					.getDefaultProcessEngine().getProcessEngineConfiguration()
					.getProcessEngineConfiguration()).getTemperatureService()
					.insert(receiver.getTemperature());
		}
	}
}
