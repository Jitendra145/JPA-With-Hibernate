package com.jitendra.jpa.hibernate.repository;

import static org.junit.Assert.assertNull;

import java.util.List;

import javax.persistence.EntityGraph;
import javax.persistence.EntityManager;
import javax.persistence.Subgraph;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;

import com.jitendra.jpa.hibernate.DemoApplication;
import com.jitendra.jpa.hibernate.entity.Course;

@RunWith(SpringRunner.class)
@SpringBootTest(classes=DemoApplication.class)
public class PerformanceTuningTest {

	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private EntityManager em;	

	/*
	@Test
	@DirtiesContext
	public void createNPlusOneProblem(){		
		List<Course> courses = em.createNamedQuery("fetch_all_courses", Course.class).getResultList();
		for(Course course:courses) {
			logger.info("Student-->{}, Course-->{}",course,course.getStudents());
		}
	}
	
	@Test
	@DirtiesContext
	public void solveNPlusOneProblem_GraphSolution(){	
		EntityGraph<Course> entityGraph = em.createEntityGraph(Course.class);
		Subgraph<Object> subgraph = entityGraph.addSubgraph("students");
		List<Course> courses = em
				.createNamedQuery("fetch_all_courses", Course.class)
				.setHint("javax.persistence.loadgraph", entityGraph)
				.getResultList();
		for(Course course:courses) {
			logger.info("Student-->{}, Course-->{}",course,course.getStudents());
		}
	}
	*/
	@Test
	@DirtiesContext
	public void solveNPlusOneProblem_Join_Fetch(){		
		List<Course> courses = em.createNamedQuery("fetch_all_courses_join_fetch", Course.class).getResultList();
		for(Course course:courses) {
			logger.info("Student-->{}, Course-->{}",course,course.getStudents());
		}
	}
}
