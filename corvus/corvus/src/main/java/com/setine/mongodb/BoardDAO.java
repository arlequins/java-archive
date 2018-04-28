package com.setine.mongodb;

import java.io.Serializable;
import java.util.ArrayList;

import com.setine.hibernate.model.Board;

public interface BoardDAO {

	Serializable insert(Serializable object, String collection_name);

	Serializable getBoard(int key, String collection_name);

	ArrayList<Board> getBoards(String collection_name);

}
