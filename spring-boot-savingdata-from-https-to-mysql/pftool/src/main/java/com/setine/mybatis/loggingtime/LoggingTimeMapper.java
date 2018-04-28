package com.setine.mybatis.loggingtime;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Param;

public interface LoggingTimeMapper {
	ArrayList<LoggingTime> getLists();
	LoggingTime getCount();
	void insertData(@Param("date")String date, @Param("active_user")String active_user, @Param("sum_count")long sum_count, @Param("max_date")String max_date, @Param("max_count")String max_count);
	ArrayList<LoggingTime> getActiveLists(@Param("date")String date);
	ArrayList<LoggingTime> getActiveLists_time(@Param("date")String date, @Param("hour")String hour);
	ArrayList<LoggingTime> getActiveLists_date(@Param("date")String date);
}
