package com.setine.hibernate.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.springframework.stereotype.Repository;

import com.setine.hibernate.model.NecisData;

@Repository
public class NecisDataDaoImpl extends AbstractDao<Integer, NecisData> implements NecisDataDao {

	@Override
	public NecisData findById(int id) {
		return getByKey(id);
	}

	@Override
	public void save(NecisData necisData) {
		persist(necisData);
	}

	@Override
	public List<NecisData> findAllNecisData() {
		Criteria criteria = createEntityCriteria();
		return (List<NecisData>) criteria.list();
	}
}