package com.setine.hibernate.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.springframework.stereotype.Repository;

import com.setine.hibernate.model.KmaData;

@Repository
public class KmaDataDaoImpl extends AbstractDao<Integer, KmaData> implements KmaDataDao {

	@Override
	public KmaData findById(int id) {
		return getByKey(id);
	}

	@Override
	public void save(KmaData kmaData) {
		persist(kmaData);
	}

	@Override
	public List<KmaData> findAllKmaData() {
		Criteria criteria = createEntityCriteria();
		return (List<KmaData>) criteria.list();
	}
}