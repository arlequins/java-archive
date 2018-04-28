package com.setine.hibernate.model;

import java.util.Date;

import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Board implements java.io.Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 2222999405331571607L;
	private int key;
	private Date date;
	private String writer;
	private String title;
	private String content;

	public Board(int key, Date date, String writer, String title, String content) {
		this.key = key;
		this.date = date;
		this.writer = writer;
		this.title = title;
		this.content = content;
	}
	public Board() {
	}
	public int getKey() {
		return key;
	}

	public void setKey(int key) {
		this.key = key;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getWriter() {
		return writer;
	}

	public void setWriter(String writer) {
		this.writer = writer;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

}
