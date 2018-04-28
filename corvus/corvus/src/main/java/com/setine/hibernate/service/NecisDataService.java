package com.setine.hibernate.service;

import java.util.List;

import com.setine.hibernate.model.NecisData;

public interface NecisDataService {

	NecisData findByKey(int key);
	List<NecisData> findAllNecisData();
	void save(NecisData necisData);
}