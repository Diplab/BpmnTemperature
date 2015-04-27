package com.diplab.activiti.engine.impl;

import java.util.List;

import org.activiti.engine.impl.ServiceImpl;
import org.activiti.engine.impl.cfg.ProcessEngineConfigurationImpl;
import org.activiti.engine.impl.interceptor.Command;
import org.activiti.engine.impl.interceptor.CommandContext;
import org.apache.ibatis.session.SqlSession;

import com.diplab.activiti.temperature.Temperature;
import com.diplab.activiti.temperature.db.TemperatureMapper;

public class TemperatureServiceImpl extends ServiceImpl {
	public TemperatureServiceImpl(
			ProcessEngineConfigurationImpl processEngineConfiguration) {
		super(processEngineConfiguration);
	}

	public List<Temperature> getTemperatures() {

		return commandExecutor.execute(new GetTemperaturesCmd());

	}

	public Temperature getLatestTemperature() {
		List<Temperature> temperatures = getTemperatures();
		return (temperatures.size() == 0) ? null : temperatures.get(0);

	}

	public void insert(Temperature temperature) {
		commandExecutor.execute(new InsertTemperatureCmd(temperature));
	}
}

class InsertTemperatureCmd implements Command<Void> {
	private Temperature temperature;

	public InsertTemperatureCmd(Temperature temperature) {
		this.temperature = temperature;
	}

	@Override
	public Void execute(CommandContext commandContext) {
		SqlSession sqlSession = commandContext.getDbSqlSession()
				.getSqlSession();
		try {

			TemperatureMapper mapper = sqlSession
					.getMapper(TemperatureMapper.class);
			mapper.insert(temperature);
			return null;
		} finally {
			sqlSession.commit();
		}
	}
}

class GetTemperaturesCmd implements Command<List<Temperature>> {

	@Override
	public List<Temperature> execute(CommandContext commandContext) {
		SqlSession sqlSession = commandContext.getDbSqlSession()
				.getSqlSession();
		try {

			TemperatureMapper mapper = sqlSession
					.getMapper(TemperatureMapper.class);

			return mapper.selectAll();
		} finally {
			sqlSession.commit();
		}
	}

}