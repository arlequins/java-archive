package com.setine.hibernate.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.setine.hibernate.dao.NecisDataDao;
import com.setine.hibernate.model.NecisData;

@Service
@Transactional
public class NecisDataServiceImpl implements NecisDataService {

	@Autowired
	private NecisDataDao dao;

	@Override
	public NecisData findByKey(int key) {
		return dao.findById(key);
	}

	@Override
	public void save(NecisData necisData) {
		dao.save(necisData);
	}

	@Override
	public List<NecisData> findAllNecisData() {
		return dao.findAllNecisData();
	}
}
