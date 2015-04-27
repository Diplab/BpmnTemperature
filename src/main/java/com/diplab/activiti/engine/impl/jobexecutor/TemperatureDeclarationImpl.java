package com.diplab.activiti.engine.impl.jobexecutor;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import com.diplab.activiti.temperature.IsSatisfy;
import com.diplab.activiti.temperature.Temperature;

public class TemperatureDeclarationImpl {
	private TemperatureDeclarationType type;
	private double condition;
	private int time;

	public TemperatureDeclarationImpl(TemperatureDeclarationType type,
			double condition) {
		super();
		this.type = type;
		this.condition = condition;
	}

	public TemperatureDeclarationImpl(TemperatureDeclarationType type,
			double condition, int time) {
		this(type, condition);
		this.time = time;
	}

	/**
	 * @return null if mode is not defined.
	 */
	public IsSatisfy prepareIsSatisfy() {
		switch (type) {
		case GREATER:
			return new IsSatisfy() {
				@Override
				public boolean isSatisfy(List<Temperature> records) {
					if (records == null || records.size() == 0)
						return false;
					return records.get(0).getTemperature() >= condition;
				}
			};
		case LESSER:
			return new IsSatisfy() {
				@Override
				public boolean isSatisfy(List<Temperature> records) {
					if (records == null || records.size() == 0)
						return false;
					return records.get(0).getTemperature() <= condition;
				}
			};
		case AVG_GREATER:
			return new IsSatisfy() {

				@Override
				public boolean isSatisfy(List<Temperature> records) {

					double avg = calAvg(records, time);
					return avg >= condition;
				}
			};
		case AVG_LESSER:
			return new IsSatisfy() {

				@Override
				public boolean isSatisfy(List<Temperature> records) {

					double avg = calAvg(records, time);
					return avg <= condition;
				}
			};
		default:
			// throw new Exception(String.format("%s is not supported",
			// type.toString()));
			return null;
		}

	}

	private static double calAvg(List<Temperature> records, final int time) {
		// Find record in range and avg
		double sum = 0;
		for (int i = 0; i < time; i++) {
			sum = sum + records.get(i).getTemperature();
		}
		return sum / time;
	}

}
