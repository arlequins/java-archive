package com.setine.hibernate.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.Generated;
import org.hibernate.annotations.GenerationTime;

@Entity
@Table(name = "necisdata")
@DynamicInsert
public class NecisData implements java.io.Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -440903405609293119L;
	private int key;
	private Date date;
	private Date savedate;
	private String magnitude;
	private String lo1;
	private String lo2;
	private String loName;

	public NecisData(int key) {
		this.key = key;
	}

	public NecisData(int key, Date date, String magnitude, String lo1, String lo2, String loName) {
		this.key = key;
		this.date = date;
		this.magnitude = magnitude;
		this.lo1 = lo1;
		this.lo2 = lo2;
		this.loName = loName;
	}

	public NecisData() {
	}

	@Id
	@Column(name = "Id", unique = true, nullable = false)
	public int getKey() {
		return key;
	}

	public void setKey(int key) {
		this.key = key;
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
	@Generated(GenerationTime.ALWAYS)
	public Date getSavedate() {
		return savedate;
	}

	public void setSavedate(Date savedate) {
		this.savedate = savedate;
	}

	@Column(name = "Magnitude")
	public String getMagnitude() {
		return magnitude;
	}

	public void setMagnitude(String magnitude) {
		this.magnitude = magnitude;
	}

	@Column(name = "Lo1")
	public String getLo1() {
		return lo1;
	}

	public void setLo1(String lo1) {
		this.lo1 = lo1;
	}

	@Column(name = "Lo2")
	public String getLo2() {
		return lo2;
	}

	public void setLo2(String lo2) {
		this.lo2 = lo2;
	}

	@Column(name = "LoName")
	public String getLoName() {
		return loName;
	}

	public void setLoName(String loName) {
		this.loName = loName;
	}

}
