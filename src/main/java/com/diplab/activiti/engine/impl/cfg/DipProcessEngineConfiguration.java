package com.diplab.activiti.engine.impl.cfg;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import org.activiti.bpmn.converter.BpmnXMLConverter;
import org.activiti.engine.ProcessEngine;
import org.activiti.engine.impl.ProcessEngineImpl;
import org.activiti.engine.impl.cfg.StandaloneProcessEngineConfiguration;
import org.activiti.engine.impl.db.DbSqlSession;
import org.activiti.engine.impl.interceptor.Command;
import org.activiti.engine.impl.interceptor.CommandContext;
import org.activiti.engine.parse.BpmnParseHandler;

import com.diplab.activiti.bpmn.converter.DiplabStartEventXMLConverter;
import com.diplab.activiti.engine.impl.TemperatureServiceImpl;
import com.diplab.activiti.engine.impl.bpmn.parser.handler.DiplabEventDefinitionParserHandler;
import com.diplab.activiti.engine.impl.bpmn.parser.handler.DiplabStartEventParserHandler;
import com.diplab.activiti.temperature.db.TemperatureMapper;

public class DipProcessEngineConfiguration extends
		StandaloneProcessEngineConfiguration {

	static {
		BpmnXMLConverter.addConverter(new DiplabStartEventXMLConverter());
	}
	TemperatureServiceImpl temperatureService = new TemperatureServiceImpl(this);

	public DipProcessEngineConfiguration() {
		this.setCustomDefaultBpmnParseHandlers(Arrays
				.<BpmnParseHandler> asList(new DiplabStartEventParserHandler()));

		this.setPostBpmnParseHandlers(Arrays
				.<BpmnParseHandler> asList(new DiplabEventDefinitionParserHandler()));

		Set<Class<?>> customMybatisMappers = new HashSet<>();
		customMybatisMappers.add(TemperatureMapper.class);

		this.customMybatisMappers = customMybatisMappers;
		databaseSchemaUpdate = "drop-create";

	}

	// Override initService
	@Override
	protected void initServices() {
		super.initServices();
		initService(temperatureService);
	};

	// getter and setter for TemperatureService

	public TemperatureServiceImpl getTemperatureService() {
		return temperatureService;
	}

	public void setTemperatureService(TemperatureServiceImpl temperatureService) {
		this.temperatureService = temperatureService;
	}

	@Override
	public ProcessEngine buildProcessEngine() {
		init();
		// new out tables
		commandExecutor.execute(getSchemaCommandConfig(), new Command<Void>() {

			@Override
			public Void execute(CommandContext commandContext) {
				DbSqlSession session = commandContext
						.getSession(DbSqlSession.class);
				session.executeSchemaResource(
						"create",
						"temperature",
						"com/diplab/activiti/temperature/db/create/activiti.h2.create.temperature.sql",
						false);
				return null;
			}
		});
		return new ProcessEngineImpl(this);
	}

}
