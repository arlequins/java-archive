package com.setine.mybatis.serverlog;

public class ServerLog {
	private String Key;
	private String Name;
	private String Time;
	private String Status;

	public ServerLog(){
		
	}
	
	public String getKey() {
		return Key;
	}
	public void setKey(String Key) {
		this.Key = Key;
	}

	public String getName() {
		return Name;
	}
	public void setName(String Name) {
		this.Name = Name;
	}
	public String getTime() {
		return Time;
	}
	public void setTime(String Time) {
		this.Time = Time;
	}
	public String getStatus() {
		return Status;
	}
	public void setStatus(String Status) {
		this.Status = Status;
	}
}
