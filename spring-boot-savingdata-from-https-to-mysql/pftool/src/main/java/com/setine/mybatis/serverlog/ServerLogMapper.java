package com.setine.mybatis.serverlog;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Param;

public interface ServerLogMapper {
	ArrayList<ServerLog> getLists();
	void updateData(@Param("Key")int Key, @Param("Time")String Time, @Param("Status")String Status);
	ServerLog getCount();
}
