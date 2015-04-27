package com.diplab.activiti.temperature;

import java.util.List;
import java.util.Map;

public abstract class TemperatureEventListener implements IsSatisfy {

	// delegate
	private IsSatisfy isSatisfy;

	public TemperatureEventListener(IsSatisfy isSatisfy) {
		this.isSatisfy = isSatisfy;
	}

	@Override
	public boolean isSatisfy(List<Temperature> records) {
		return isSatisfy.isSatisfy(records);
	}

	/**
	 * If {@link #isSatisfy}, this method will be called.
	 * 
	 * @param records
	 */
	public abstract void activate(List<Temperature> records);

	/**
	 * After {@link #activate(Map)} is called, should trigger again if
	 * {@link #isSatisfy}
	 * 
	 * @return true if it should trigger again
	 */
	public abstract boolean isEnd();

}
