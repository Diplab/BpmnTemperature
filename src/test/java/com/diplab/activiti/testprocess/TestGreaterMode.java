package com.diplab.activiti.testprocess;

import org.activiti.engine.ProcessEngine;
import org.activiti.engine.impl.cfg.ProcessEngineConfigurationImpl;

import com.diplab.activiti.engine.impl.cfg.DipInMemProcessEngineConfiguration;

public class TestGreaterMode {


	public static void main(String[] args) throws InterruptedException {
		ProcessEngineConfigurationImpl config = new DipInMemProcessEngineConfiguration();

		config.setJobExecutorActivate(true);

		final ProcessEngine processEngine = config.buildProcessEngine();

		processEngine
				.getRepositoryService()
				.createDeployment()
				.disableSchemaValidation()
				.disableBpmnValidation()
				.addClasspathResource(
						"com/diplab/activiti/temperature/process/SchedulerProcess.bpmn")
				.addClasspathResource("testpartdemo.bpmn20.xml").deploy();

		processEngine.getRuntimeService().startProcessInstanceByKey(
				"schedulerProcess");

	}
}
