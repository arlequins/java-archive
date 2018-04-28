package com.setine.mybatis.serverlog;

import java.util.ArrayList;

public interface ServerLogDAO {

	public ArrayList<ServerLog> getLists();
	public void updateData(int Key, String Time, String Status);
	ServerLog getCount();
}
