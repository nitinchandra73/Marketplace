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
@NamedQueries({
	@NamedQuery(name="EmployerTable.LIST_ALL_EMPLOYERS", query="from EmployerTable"),
	@NamedQuery(name="EmployerTable.listEmployerByEin",query="select e from EmployerTable e where e.ein= :ein"),
	@NamedQuery(name="EmployerTable.listEmployerById",query="select e from EmployerTable e where e.id= :id")
	})
public class EmployerTable {

	String name;
	String ein;
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Id
	int id;
	@OneToMany
	List<ProjectTable> projects;
	
	public EmployerTable() {}
	public EmployerTable(Employer employer) {
		this.name=employer.getName();
		this.ein=employer.getEin();
	}
	
	@Override
	public String toString() {
		return "EmployerTable [name=" + name + ", ein=" + ein + ", id=" + id + ", projects=" + projects + "]";
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
