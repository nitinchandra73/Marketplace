package com.intuit.cg.backendtechassessment.DAOImpl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.intuit.cg.backendtechassessment.DAO.ProjectDao;
import com.intuit.cg.backendtechassessment.controller.entity.Project;
import com.intuit.cg.backendtechassessment.dataaccess.entity.EmployerTable;
import com.intuit.cg.backendtechassessment.dataaccess.entity.ProjectTable;
import com.intuit.cg.backendtechassessment.exception.ErrorCodes;
import com.intuit.cg.backendtechassessment.exception.UserException;

@Repository
@Transactional
public class ProjectDaoImpl implements ProjectDao {

	@Autowired
	private EntityManager entityManager;
	
	@Override
	public Project addProject(Project project, EmployerTable employerTable)  {
		
		ProjectTable projectTable = new ProjectTable(project,employerTable);
		LOGGER.info("Persisting projectTable with data: "+projectTable.toString());
		entityManager.persist(projectTable);
		project.setId(projectTable.getId());
		LOGGER.info("Persisted project table and returning Project: "+project.toString());
		return project;
	}

	@Override
	public Project getProject(int employerId, Integer projectId) throws UserException {
		LOGGER.info("Queried project table for project by id: "+projectId);
		List<ProjectTable> projectTables = entityManager.createNamedQuery("projectTable.SELECT_PROJECT_BY_ID", ProjectTable.class).setParameter("id", projectId).getResultList();
		if(projectTables.size()>0) {
			ProjectTable projectTable = projectTables.get(0);
			if(projectTable.getEmployer().getId()==employerId) {
				Project responseProject = new Project(projectTable);
				LOGGER.info("Response project: "+responseProject.toString());
				return responseProject;
			}
			LOGGER.error("ProjectId: "+projectId+" dosent match EmployerId: "+employerId+". With error code:"+ErrorCodes.MISSMATCH_EMPLOYER_ID_AND_PROJECT_ID);
			throw new UserException("ProjectId: "+projectId+" dosent match EmployerId: "+employerId, ErrorCodes.MISSMATCH_EMPLOYER_ID_AND_PROJECT_ID);
		}
		LOGGER.error("Project dosent exist for ProjectId: "+projectId+". with error code: "+ErrorCodes.MISSING_PROJECT_FOR_ID);
		throw new UserException("Project dosent exist for ProjectId: "+projectId, ErrorCodes.MISSING_PROJECT_FOR_ID);

	}
}
