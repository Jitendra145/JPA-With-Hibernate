package com.jitendra.jpa.hibernate.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.jitendra.jpa.hibernate.DemoApplication;
import com.jitendra.jpa.hibernate.entity.Course;

@RunWith(SpringRunner.class)
@SpringBootTest(classes=DemoApplication.class)
public class CourseNativeTest {

    private Logger logger = LoggerFactory.getLogger(this.getClass());
    
	@Autowired
	EntityManager em;
	
	
	@Test
	public void jpql_basic() {
		List resultList = em.createNamedQuery("fetch_all_courses").getResultList();
		logger.info("select c from Course c --> {}",resultList);
	}
	
	@Test
	public void jpql_typed() {
		TypedQuery<Course> typedQuery = em.createNamedQuery("fetch_all_courses",Course.class);
		List<Course> resultList = typedQuery.getResultList();
		logger.info("select c from Course c Typed --> {}",resultList);
	}
	
	@Test
	public void jpql_where() {
		TypedQuery<Course> typedQuery = em.createNamedQuery("fetch_all_courses_with_100",Course.class);
		List<Course> resultList = typedQuery.getResultList();
		logger.info("select c from Course c where name like '%100%'--> {}",resultList);
	}
}
