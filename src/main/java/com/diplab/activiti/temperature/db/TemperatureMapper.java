package com.diplab.activiti.temperature.db;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import com.diplab.activiti.temperature.Temperature;

public interface TemperatureMapper {

	@Select("SELECT * FROM DIP_TEMPERATURE ORDER BY SCAN_TIME_ DESC  LIMIT 60")
	@Results(value = {
			@Result(column = "TEMPERATURE_", property = "temperature"),
			@Result(column = "SCAN_TIME_", property = "time") })
	List<Temperature> selectAll();

	@Insert("INSERT INTO DIP_TEMPERATURE (TEMPERATURE_,SCAN_TIME_) VALUES (#{temperature},#{time})")
	void insert(Temperature temperature);
}
