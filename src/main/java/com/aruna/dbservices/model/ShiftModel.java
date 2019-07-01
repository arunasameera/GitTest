package com.aruna.dbservices.model;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;


@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({ "shift"})
public class ShiftModel implements Serializable {

	@JsonProperty("shift")
	private String shift;
	
	private final static long serialVersionUID = -8348726304392652259L;
	
	@JsonProperty("shift")
	public String getShift() {
		return shift;
	}

	@JsonProperty("shift")
	public void setShift(String shift) {
		this.shift = shift;
	}

	
	
	
}