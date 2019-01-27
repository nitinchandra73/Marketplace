package com.intuit.cg.backendtechassessment.DAO;

import java.util.List;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.intuit.cg.backendtechassessment.controller.entity.Project;
import com.intuit.cg.backendtechassessment.dataaccess.entity.EmployerTable;
import com.intuit.cg.backendtechassessment.dataaccess.entity.ProjectTable;
import com.intuit.cg.backendtechassessment.exception.ErrorCodes;
import com.intuit.cg.backendtechassessment.exception.UserException;

@Repository
@Transactional
public class ProjectDaoImpl {

	@Autowired
	private EntityManager entityManager;
	
	public Project addProject(Project project, EmployerTable employerTable)  {
		// TODO Auto-generated method stub
		
		ProjectTable projectTable = new ProjectTable(project,employerTable);
		entityManager.persist(projectTable);
		project.setId(projectTable.getId());
		//entityManager.createNamedQuery("ProjectTable.insertNewProject").setParameter("name", project.getName()).setParameter("ein", project.getEin()).get;
		return project;
	}

	public Project getProject(int employerId, Integer projectid) throws UserException {
		// TODO Auto-generated method stub
		List<ProjectTable> projectTables = entityManager.createNamedQuery("projectTable.SELECT_PROJECT_BY_ID", ProjectTable.class).setParameter("id", projectid).getResultList();
		if(projectTables.size()>0) {
			ProjectTable projectTable = projectTables.get(0);
			if(projectTable.getEmployer().getId()==employerId) {
				Project responseProject = new Project(projectTable);
				return responseProject;
			}
			throw new UserException("ProjectId: "+projectid+" dosent match EmployerId: "+employerId, ErrorCodes.MISSMATCH_EMPLOYER_ID_AND_PROJECT_ID);
		}
		throw new UserException("Project dosent exist for ProjectId: "+projectid, ErrorCodes.MISSING_PROJECT_FOR_ID);

	}
}
