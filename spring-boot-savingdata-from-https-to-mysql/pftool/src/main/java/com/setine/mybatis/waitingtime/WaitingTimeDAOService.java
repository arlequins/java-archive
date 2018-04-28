package com.setine.mybatis.waitingtime;

import java.util.ArrayList;

import javax.annotation.Resource;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class WaitingTimeDAOService implements WaitingTimeDAO {

	@Autowired
	@Resource(name="sqlSession_db")
	private SqlSession sqlSession;

	@Override
	public WaitingTime getLastTime() {
		WaitingTime result = new WaitingTime();
		WaitingTimeMapper WaitingTimeMapper = sqlSession.getMapper(WaitingTimeMapper.class);
		result = WaitingTimeMapper.getLastTime();
		return result;
	}

	@Override
	public ArrayList<WaitingTime> getList(String year, String month) {
		ArrayList<WaitingTime> result = new ArrayList<WaitingTime>();
		WaitingTimeMapper WaitingTimeMapper = sqlSession.getMapper(WaitingTimeMapper.class);
		result = WaitingTimeMapper.getList(year, month);
		return result;
	}
	
	@Override
	public void insertData(ArrayList<WaitingTime> WaitingTime) {
		WaitingTimeMapper WaitingTimeMapper = sqlSession.getMapper(WaitingTimeMapper.class);
		WaitingTimeMapper.insertData(WaitingTime);
	}
	@Override
	public void insertData_one(WaitingTime WaitingTime) {
		WaitingTimeMapper WaitingTimeMapper = sqlSession.getMapper(WaitingTimeMapper.class);
		WaitingTimeMapper.insertData_one(WaitingTime);
	}
}
