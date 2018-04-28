package com.setine.mongodb;

import java.io.Serializable;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Repository;

import com.setine.hibernate.model.Board;

@Repository
public class BoardDAOImpl implements BoardDAO {

	@Autowired
	MongoTemplate mongoTemplate;

	@Override
	public Serializable insert(Serializable object, String collection_name) {
		mongoTemplate.insert(object, collection_name);
		return object;
	}

	@Override
	public Serializable getBoard(int key,String collection_name) {
		return mongoTemplate.findById(key, Board.class, collection_name);
	}

	@Override
	public ArrayList<Board> getBoards(String collection_name) {
		return (ArrayList<Board>) mongoTemplate.findAll(Board.class, collection_name);
	}
}