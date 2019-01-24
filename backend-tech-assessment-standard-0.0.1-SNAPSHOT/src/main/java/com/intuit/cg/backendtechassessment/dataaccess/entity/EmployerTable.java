package com.intuit.cg.backendtechassessment.dataaccess.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Table;

import org.hibernate.annotations.NamedQuery;
import org.springframework.data.annotation.Id;

@Entity
@Table(name="EmployerTable")
@NamedQuery(query="", name = "")
public class EmployerTable {

	String name;
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	long id;
	String projectName;
	String projectDescription;
	
	
}
