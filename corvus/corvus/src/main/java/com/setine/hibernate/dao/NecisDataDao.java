package com.setine.hibernate.dao;

import java.util.List;

import com.setine.hibernate.model.NecisData;

public interface NecisDataDao {

	NecisData findById(int id);

	List<NecisData> findAllNecisData();

	void save(NecisData necisData);

}
