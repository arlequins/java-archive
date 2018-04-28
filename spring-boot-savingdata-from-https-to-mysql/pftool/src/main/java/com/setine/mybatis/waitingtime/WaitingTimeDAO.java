package com.setine.mybatis.waitingtime;

import java.util.ArrayList;

public interface WaitingTimeDAO {

	WaitingTime getLastTime();
	void insertData(ArrayList<WaitingTime> waitingTime);
	ArrayList<WaitingTime> getList(String year, String month);
	void insertData_one(WaitingTime WaitingTime);
}
