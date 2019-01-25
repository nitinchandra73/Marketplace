package com.intuit.cg.backendtechassessment.dataaccess.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
@Entity
public class BidTable {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	public long id;
	public double bidAmount;
}
