package com.setine.hibernate.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.DynamicInsert;

@Entity
@Table(name = "kmadata")
@DynamicInsert
public class KmaData implements java.io.Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -7378783840450177468L;
	private int key;
	private int type;
	private Date pdate;
	private Date date;
	private Date saveddate;
	private String magnitude;
	private String location;
	private String etc;

	public KmaData(int key) {
		this.key = key;
	}

	public KmaData(int type, Date pdate, String etc) {
		this.type = type;
		this.pdate = pdate;
		this.etc = etc;
	}
	
	public KmaData(int type, Date pdate, Date date, String magnitude, String location, String etc) {
		this.type = type;
		this.pdate = pdate;
		this.date = date;
		this.magnitude = magnitude;
		this.location = location;
		this.etc = etc;
	}

	public KmaData() {
	}

	@Id
	@Column(name = "Id", unique = true, updatable = false, insertable = false, nullable = false)
	@GeneratedValue(strategy=GenerationType.AUTO)
	public int getKey() {
		return key;
	}

	public void setKey(int key) {
		this.key = key;
	}

	@Column(name = "type")
	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	@Column(name = "pdate", columnDefinition = "TIMESTAMP")
	@Temporal(TemporalType.TIMESTAMP)
	public Date getPdate() {
		return pdate;
	}

	public void setPdate(Date pdate) {
		this.pdate = pdate;
	}

	@Column(name = "date", columnDefinition = "TIMESTAMP")
	@Temporal(TemporalType.TIMESTAMP)
	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP", name = "SaveDate", updatable = false, insertable = false, nullable = false)
	public Date getSaveddate() {
		return saveddate;
	}

	public void setSaveddate(Date saveddate) {
		this.saveddate = saveddate;
	}

	@Column(name = "Magnitude")
	public String getMagnitude() {
		return magnitude;
	}

	public void setMagnitude(String magnitude) {
		this.magnitude = magnitude;
	}

	@Column(name = "Location")
	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	@Column(name = "etc")
	public String getEtc() {
		return etc;
	}

	public void setEtc(String etc) {
		this.etc = etc;
	}

}
