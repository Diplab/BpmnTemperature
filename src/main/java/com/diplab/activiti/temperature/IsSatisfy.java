package com.diplab.activiti.temperature;

import java.util.List;

public interface IsSatisfy {
	
	/**
	 * 
	 * @param records should be sorted Latest > Oldest
	 * @return
	 */
	boolean isSatisfy(List<Temperature> records);
}
