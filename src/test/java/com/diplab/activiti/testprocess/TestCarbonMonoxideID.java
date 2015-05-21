package com.diplab.activiti.testprocess;

import org.activiti.engine.ProcessEngine;
import org.activiti.engine.impl.cfg.ProcessEngineConfigurationImpl;
import org.activiti.engine.impl.cfg.StandaloneInMemProcessEngineConfiguration;

import com.diplab.activiti.carbon_monoxide.CarbonMonoxideReceiver;
import com.diplab.activiti.carbon_monoxide.CarbonMonoxideReceiverImp;


public class TestCarbonMonoxideID {

	public static void main(String[] args) throws InterruptedException {
		ProcessEngineConfigurationImpl config = new StandaloneInMemProcessEngineConfiguration();

		config.setJobExecutorActivate(true);

		final ProcessEngine processEngine = config.buildProcessEngine();

		processEngine.getRepositoryService().createDeployment()
				.disableSchemaValidation().disableBpmnValidation()
				.addClasspathResource("CoID.bpmn").deploy();
		
		CarbonMonoxideReceiver.addReceiver(new CarbonMonoxideReceiverImp("Co-01"));
		CarbonMonoxideReceiver.addReceiver(new CarbonMonoxideReceiverImp("Co-02"));
		CarbonMonoxideReceiver.addReceiver(new CarbonMonoxideReceiverImp("Co-03"));

		processEngine.getRuntimeService().startProcessInstanceByKey(
				"myProcess");

	}
}
