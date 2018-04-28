package com.setine.mybatis.loggingtime;

public class LoggingTime {
	private String Key;
	private String Date;
	private String Time;
	private String count;
	private String sum_count;
	private String max_date;
	private String max_count;
	
	
	public String getMax_date() {
		return max_date;
	}
	public void setMax_date(String max_date) {
		this.max_date = max_date;
	}
	public String getMax_count() {
		return max_count;
	}
	public void setMax_count(String max_count) {
		this.max_count = max_count;
	}
	public String getSum_count() {
		return sum_count;
	}
	public void setSum_count(String sum_count) {
		this.sum_count = sum_count;
	}
	public String getKey() {
		return Key;
	}
	public void setKey(String key) {
		Key = key;
	}
	public String getDate() {
		return Date;
	}
	public void setDate(String date) {
		Date = date;
	}
	public String getTime() {
		return Time;
	}
	public void setTime(String time) {
		Time = time;
	}
	public String getCount() {
		return count;
	}
	public void setCount(String count) {
		this.count = count;
	}


}
