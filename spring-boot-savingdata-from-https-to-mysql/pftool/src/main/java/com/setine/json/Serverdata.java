package com.setine.json;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Serverdata {
	private List<result> result;

	@JsonProperty("result")
	public List<result> getResult() {
		return result;
	}

	@JsonProperty("result")
	public void setResult(List<result> result) {
		this.result = result;
	}

}
