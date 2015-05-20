package com.diplab.activiti.testprocess;

import org.activiti.engine.ProcessEngine;
import org.activiti.engine.impl.cfg.ProcessEngineConfigurationImpl;
import org.activiti.engine.impl.cfg.StandaloneInMemProcessEngineConfiguration;

import com.diplab.activiti.smoke.SmokeReceiver;
import com.diplab.activiti.smoke.SmokeReceiverImp;

public class TestSmokeId {

	public static void main(String[] args) throws InterruptedException {
		ProcessEngineConfigurationImpl config = new StandaloneInMemProcessEngineConfiguration();

		config.setJobExecutorActivate(true);

		final ProcessEngine processEngine = config.buildProcessEngine();

		processEngine.getRepositoryService().createDeployment()
				.disableSchemaValidation().disableBpmnValidation()
				.addClasspathResource("smokeID.bpmn").deploy();
		
		SmokeReceiver.addReceiver(new SmokeReceiverImp("Smoke-01"));
		SmokeReceiver.addReceiver(new SmokeReceiverImp("Smoke-02"));
		SmokeReceiver.addReceiver(new SmokeReceiverImp("Smoke-03"));

		processEngine.getRuntimeService().startProcessInstanceByKey(
				"myProcess");

	}
}
