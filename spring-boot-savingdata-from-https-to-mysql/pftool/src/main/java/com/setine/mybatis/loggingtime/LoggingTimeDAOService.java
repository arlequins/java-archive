package com.setine.mybatis.loggingtime;

import java.util.ArrayList;

import javax.annotation.Resource;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class LoggingTimeDAOService implements LoggingTimeDAO {

	@Autowired
	@Resource(name="sqlSession_db")
	private SqlSession sqlSession;

	@Override
	public LoggingTime getCount() {
		LoggingTime result = new LoggingTime();
		LoggingTimeMapper ServerLogMapper = sqlSession.getMapper(LoggingTimeMapper.class);
		result = ServerLogMapper.getCount();
		return result;
	}
	@Override
	public ArrayList<LoggingTime> getLists() {
		ArrayList<LoggingTime> result = new ArrayList<LoggingTime>();
		LoggingTimeMapper ServerLogMapper = sqlSession.getMapper(LoggingTimeMapper.class);
		result = ServerLogMapper.getLists();
		return result;
	}
	@Override
	public ArrayList<LoggingTime> getActiveLists(String Date) {
		ArrayList<LoggingTime> result = new ArrayList<LoggingTime>();
		LoggingTimeMapper ServerLogMapper = sqlSession.getMapper(LoggingTimeMapper.class);
		result = ServerLogMapper.getActiveLists(Date);
		return result;
	}
	@Override
	public ArrayList<LoggingTime> getActiveLists_date(String Date) {
		ArrayList<LoggingTime> result = new ArrayList<LoggingTime>();
		LoggingTimeMapper ServerLogMapper = sqlSession.getMapper(LoggingTimeMapper.class);
		result = ServerLogMapper.getActiveLists_date(Date);
		return result;
	}
	@Override
	public ArrayList<LoggingTime> getActiveLists_time(String Date, String hour) {
		ArrayList<LoggingTime> result = new ArrayList<LoggingTime>();
		LoggingTimeMapper ServerLogMapper = sqlSession.getMapper(LoggingTimeMapper.class);
		result = ServerLogMapper.getActiveLists_time(Date,hour);
		return result;
	}

	@Override
	public void insertData(String date, String active_user, long sum_count, String max_date, String max_count) {
		LoggingTimeMapper ServerLogMapper = sqlSession.getMapper(LoggingTimeMapper.class);
		ServerLogMapper.insertData(date,active_user,sum_count,max_date,max_count );
	}
}
