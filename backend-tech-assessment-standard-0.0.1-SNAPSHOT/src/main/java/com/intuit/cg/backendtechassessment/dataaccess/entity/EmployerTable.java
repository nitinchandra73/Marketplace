package com.intuit.cg.backendtechassessment.dataaccess.entity;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.NamedQuery;

@Entity
@Table(name="EmployerTable")
//@NamedQuery(query="", name = "")
public class EmployerTable {

	String name;
	
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Id
	long id;
//	String projectName;
//	String projectDescription;
	@OneToMany
	List<ProjectsTable> projects;
	
	public List<ProjectsTable> getProjects() {
		return projects;
	}
	public void setProjects(List<ProjectsTable> projects) {
		this.projects = projects;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	
	
	
}
