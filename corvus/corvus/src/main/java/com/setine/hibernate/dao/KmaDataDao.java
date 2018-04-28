package com.setine.hibernate.dao;

import java.util.List;

import com.setine.hibernate.model.KmaData;

public interface KmaDataDao {

	KmaData findById(int id);

	void save(KmaData kmaData);

	List<KmaData> findAllKmaData();

}
