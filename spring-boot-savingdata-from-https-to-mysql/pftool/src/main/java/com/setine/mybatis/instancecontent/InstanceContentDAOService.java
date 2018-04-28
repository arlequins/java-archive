package com.setine.mybatis.instancecontent;

import java.util.ArrayList;

import javax.annotation.Resource;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class InstanceContentDAOService implements InstanceContentDAO {

	@Autowired
	@Resource(name="sqlSession_db")
	private SqlSession sqlSession;

	@Override
	public ArrayList<InstanceContent> getList() {
		ArrayList<InstanceContent> result = new ArrayList<InstanceContent>();
		InstanceContentMapper WaitingTimeMapper = sqlSession.getMapper(InstanceContentMapper.class);
		result = WaitingTimeMapper.getList();
		return result;
	}
}
