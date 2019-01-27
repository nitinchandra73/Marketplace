package com.intuit.cg.backendtechassessment.dataaccess.entity;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.validator.constraints.UniqueElements;

import com.intuit.cg.backendtechassessment.controller.entity.Employer;


@Entity
@Table(name="EmployerTable")
//@NamedQuery(query="", name = "")

@NamedQueries({
	@NamedQuery(name="EmployerTable.LIST_ALL_EMPLOYERS", query="from EmployerTable"),
	@NamedQuery(name="EmployerTable.listEmployerByEin",query="select e from EmployerTable e where e.ein= :ein"),
	@NamedQuery(name="EmployerTable.listEmployerById",query="select e from EmployerTable e where e.id= :id")
	
	//@NamedQuery(name="EmployerTable.insertNewEmployer",query="insert into EmployerTable e values e.name=:name e.ein= :ein ")
	})
public class EmployerTable {

	String name;
	//@UniqueElements
	String ein;
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Id
	int id;
//	String projectName;
//	String projectDescription;
	@OneToMany
	List<ProjectTable> projects;
	
	public EmployerTable() {}
	public EmployerTable(Employer employer) {
		this.name=employer.getName();
		this.ein=employer.getEin();
	}
	public String getEin() {
		return ein;
	}
	public void setEin(String ein) {
		this.ein = ein;
	}
	public List<ProjectTable> getProjects() {
		return projects;
	}
	public void setProjects(List<ProjectTable> projects) {
		this.projects = projects;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	
	
}
