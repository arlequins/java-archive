package com.setine.hibernate.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.setine.hibernate.dao.KmaDataDao;
import com.setine.hibernate.model.KmaData;

@Service
@Transactional
public class KmaDataServiceImpl implements KmaDataService {

	@Autowired
	private KmaDataDao dao;

	@Override
	public KmaData findByKey(int key) {
		return dao.findById(key);
	}

	@Override
	public void save(KmaData kmaDataData) {
		dao.save(kmaDataData);
	}

	@Override
	public List<KmaData> findAllKmaData() {
		return dao.findAllKmaData();
	}
}
