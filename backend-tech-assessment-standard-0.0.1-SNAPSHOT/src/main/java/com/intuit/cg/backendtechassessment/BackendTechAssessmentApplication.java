package com.intuit.cg.backendtechassessment;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.sql.DataSource;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import com.zaxxer.hikari.HikariDataSource;

@SpringBootApplication
@ComponentScan(basePackages = {"com.intuit.cg"})
public class BackendTechAssessmentApplication {
	 @Autowired
	    DataSource dataSource; 
	 @PersistenceContext
	    private EntityManager em;

	public static void main(String[] args) {
		SpringApplication.run(BackendTechAssessmentApplication.class, args);
	}

}
