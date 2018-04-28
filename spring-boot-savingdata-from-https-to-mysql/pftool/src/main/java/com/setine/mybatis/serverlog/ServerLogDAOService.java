package com.setine.mybatis.serverlog;

import java.util.ArrayList;

import javax.annotation.Resource;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class ServerLogDAOService implements ServerLogDAO {

	@Autowired
	@Resource(name="sqlSession_db")
	private SqlSession sqlSession;

	@Override
	public ServerLog getCount() {
		ServerLog result = new ServerLog();
		ServerLogMapper ServerLogMapper = sqlSession.getMapper(ServerLogMapper.class);
		result = ServerLogMapper.getCount();
		return result;
	}
	@Override
	public ArrayList<ServerLog> getLists() {
		ArrayList<ServerLog> result = new ArrayList<ServerLog>();
		ServerLogMapper ServerLogMapper = sqlSession.getMapper(ServerLogMapper.class);
		result = ServerLogMapper.getLists();
		return result;
	}

	@Override
	public void updateData(int Key, String Time, String Status) {
		ServerLogMapper ServerLogMapper = sqlSession.getMapper(ServerLogMapper.class);
		ServerLogMapper.updateData(Key, Time, Status);
	}
}
