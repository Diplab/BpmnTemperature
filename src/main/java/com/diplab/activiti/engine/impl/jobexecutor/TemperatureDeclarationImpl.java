package com.diplab.activiti.engine.impl.jobexecutor;

import java.util.List;

import org.activiti.engine.ActivitiIllegalArgumentException;

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
		case NONE:
			return new IsSatisfy() {

				@Override
				public boolean isSatisfy(List<Temperature> records) {
					return true;
				}
			};
		default:
			throw new ActivitiIllegalArgumentException(String.format(
					"%s is not supported", type.toString()));
		}

	}

	private static double calAvg(List<Temperature> records, final int time) {
		if (time == 0) {
			throw new ActivitiIllegalArgumentException(
					"time should bigger than 1 in this mode");
		}
		// Find record in range and avg
		double sum = 0;
		for (int i = 0; i < time; i++) {
			sum = sum + records.get(i).getTemperature();
		}
		return sum / time;
	}

}
