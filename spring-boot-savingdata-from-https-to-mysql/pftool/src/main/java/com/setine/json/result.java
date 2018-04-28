package com.setine.json;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class result {
	private String type;
	private String ready;
	private String name;
	private String host;
	private String serverset;
	private String diasble;

	@JsonProperty("type")
	public String getType() {
		return type;
	}
	@JsonProperty("type")	
	public void setType(String type) {
		this.type = type;
	}
	@JsonProperty("ready")
	public String getReady() {
		return ready;
	}
	@JsonProperty("ready")	
	public void setReady(String ready) {
		this.ready = ready;
	}
	@JsonProperty("name")
	public String getName() {
		return name;
	}
	@JsonProperty("name")	
	public void setName(String name) {
		this.name = name;
	}
	@JsonProperty("host")	
	public String getHost() {
		return host;
	}
	@JsonProperty("host")	
	public void setHost(String host) {
		this.host = host;
	}
	@JsonProperty("serverset")
	public String getServerset() {
		return serverset;
	}
	@JsonProperty("serverset")	
	public void setServerset(String serverset) {
		this.serverset = serverset;
	}
	@JsonProperty("disable")	
	public String getDiasble() {
		return diasble;
	}
	@JsonProperty("disable")	
	public void setDiasble(String diasble) {
		this.diasble = diasble;
	}
}
