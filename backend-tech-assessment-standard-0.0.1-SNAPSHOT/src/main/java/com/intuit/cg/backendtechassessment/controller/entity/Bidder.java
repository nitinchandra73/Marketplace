package com.intuit.cg.backendtechassessment.controller.entity;

import javax.validation.constraints.NotBlank;

public class Bidder {
	Integer id;
	@NotBlank
	String name;
	@NotBlank
	String ein;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEin() {
		return ein;
	}
	public void setEin(String ein) {
		this.ein = ein;
	}
	
	
}
