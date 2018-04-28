package com.setine.mybatis.loggingtime;

import java.util.ArrayList;

public interface LoggingTimeDAO {

	public ArrayList<LoggingTime> getLists();
	LoggingTime getCount();
	ArrayList<LoggingTime> getActiveLists(String Date);
	ArrayList<LoggingTime> getActiveLists_time(String Date, String hour);
	ArrayList<LoggingTime> getActiveLists_date(String Date);
	void insertData(String date, String active_user, long sum_count, String max_date, String max_count);
}
