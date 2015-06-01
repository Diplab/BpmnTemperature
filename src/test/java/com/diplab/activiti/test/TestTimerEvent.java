package com.diplab.activiti.test;

import org.activiti.engine.ProcessEngine;
import org.activiti.engine.impl.cfg.ProcessEngineConfigurationImpl;
import org.activiti.engine.impl.cfg.StandaloneProcessEngineConfiguration;
import org.activiti.engine.impl.interceptor.Command;
import org.activiti.engine.impl.interceptor.CommandContext;

import com.diplab.activiti.Constant.TemperatureMode;
import com.diplab.activiti.engine.impl.jobexecutor.TemperatureDeclarationImpl;
import com.diplab.activiti.engine.impl.jobexecutor.TemperatureStartEventJobHandler;
import com.diplab.activiti.engine.impl.persistence.entity.TemperatureEntity;

public class TestTimerEvent {

	public static void main(String[] args) throws InterruptedException {
		ProcessEngineConfigurationImpl config = new StandaloneProcessEngineConfiguration();
		config.setJdbcUrl("jdbc:h2:tcp://localhost/Activiti");
		config.setDatabaseSchemaUpdate("drop-create");
		config.setAsyncExecutorActivate(true);
		config.setAsyncExecutorEnabled(true);

		// config.setJobExecutorActivate(true);

		final ProcessEngine processEngine = config.buildProcessEngine();

		// processEngine.getRepositoryService().createDeployment()
		// .disableSchemaValidation().disableBpmnValidation()
		// .addClasspathResource("timerStart.bpmn").deploy();

		processEngine.getManagementService().executeCommand(
				new Command<Void>() {

					@Override
					public Void execute(CommandContext commandContext) {
						System.out.println("HI");
						TemperatureDeclarationImpl declaration = new TemperatureDeclarationImpl(
								35, TemperatureMode.EQUAL,
								TemperatureStartEventJobHandler.TYPE, "2");
						new TemperatureEntity(declaration, null).insert();

						return null;
					}
				});
	}
}
