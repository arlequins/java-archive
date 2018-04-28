package com.setine.hibernate.service;

import java.util.List;

import com.setine.hibernate.model.KmaData;

public interface KmaDataService {

	KmaData findByKey(int key);
	List<KmaData> findAllKmaData();
	void save(KmaData kmaData);
}