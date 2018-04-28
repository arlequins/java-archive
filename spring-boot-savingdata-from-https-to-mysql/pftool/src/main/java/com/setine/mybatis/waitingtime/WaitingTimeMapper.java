package com.setine.mybatis.waitingtime;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Param;

public interface WaitingTimeMapper {
	void insertData(ArrayList<WaitingTime> waitingTime);
	WaitingTime getLastTime();
	ArrayList<WaitingTime> getList(@Param("year")String year, @Param("month")String month);
	void insertData_one(@Param("WaitingTime")WaitingTime waitingTime);
}
