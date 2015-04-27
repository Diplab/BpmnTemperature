package com.diplab.activiti.temperature.delegate;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import org.activiti.engine.ProcessEngines;
import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.delegate.JavaDelegate;

import com.diplab.activiti.engine.impl.cfg.DipProcessEngineConfiguration;
import com.diplab.activiti.temperature.Temperature;
import com.diplab.activiti.temperature.TemperatureEventListener;

public class SchedulerTask implements JavaDelegate {
	static List<TemperatureEventListener> listeners = Collections
			.synchronizedList(new ArrayList<TemperatureEventListener>());

	@Override
	public void execute(DelegateExecution execution) throws Exception {
		System.out.println(SchedulerTask.class.getName());
		while (true) {
			Thread.sleep(1000);
			List<Temperature> temperatures = ((DipProcessEngineConfiguration) ProcessEngines
					.getDefaultProcessEngine().getProcessEngineConfiguration()
					.getProcessEngineConfiguration()).getTemperatureService()
					.getTemperatures();

			for (Iterator<TemperatureEventListener> iterator = listeners
					.iterator(); iterator.hasNext();) {
				TemperatureEventListener temperatureEventListener = iterator
						.next();
				if (temperatureEventListener.isSatisfy(temperatures)) {
					temperatureEventListener.activate(temperatures);
					if (temperatureEventListener.isEnd()) {
						iterator.remove();
					}
				}

			}
		}
	}

	public static void addTemperatureEventListener(
			TemperatureEventListener listener) {
		listeners.add(listener);
	}

}
