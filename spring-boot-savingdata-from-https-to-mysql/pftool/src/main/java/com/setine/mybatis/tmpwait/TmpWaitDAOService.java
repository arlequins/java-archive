package com.setine.mybatis.tmpwait;

import java.util.ArrayList;

import javax.annotation.Resource;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class TmpWaitDAOService implements TmpWaitDAO {

	@Autowired
	@Resource(name="sqlSession_db")
	private SqlSession sqlSession;

	@Override
	public ArrayList<TmpWait> getList(String this_year, String this_month) {
		ArrayList<TmpWait> result = new ArrayList<TmpWait>();
		TmpWaitMapper WaitingTimeMapper = sqlSession.getMapper(TmpWaitMapper.class);
		result = WaitingTimeMapper.getList();
		return result;
	}
}
