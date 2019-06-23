package com.jitendra.jpa.hibernate.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

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
@SpringBootTest(classes = DemoApplication.class)
public class CriteriaQueryTest {

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	EntityManager em;

	@Test
	public void criteria_basic() {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Course> cq = cb.createQuery(Course.class);	
		Root<Course> courseRoot = cq.from(Course.class);
		TypedQuery<Course> typedQuery = em.createQuery(cq.select(courseRoot));
		List<Course> resultList = typedQuery.getResultList();
		logger.info("criteria query-->{}", resultList);
	}

	@Test
	public void all_courses_with_100_steps() {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Course> cq = cb.createQuery(Course.class);	
		Root<Course> courseRoot = cq.from(Course.class);
		Predicate like = cb.like(courseRoot.get("name"), "%100%");
		cq.where(like);
		TypedQuery<Course> typedQuery = em.createQuery(cq.select(courseRoot));
		List<Course> resultList = typedQuery.getResultList();
		logger.info("criteria query-->{}", resultList);
	}
	
	@Test
	public void all_courses_without_student() {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Course> cq = cb.createQuery(Course.class);	
		Root<Course> courseRoot = cq.from(Course.class);
		Predicate students = cb.isEmpty(courseRoot.get("students"));
		cq.where(students);
		TypedQuery<Course> typedQuery = em.createQuery(cq.select(courseRoot));
		List<Course> resultList = typedQuery.getResultList();
		logger.info("criteria query-->{}", resultList);
	}
	
	@Test
	public void join() {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Course> cq = cb.createQuery(Course.class);	
		Root<Course> courseRoot = cq.from(Course.class);
		Join<Object, Object> join = courseRoot.join("students");		
		TypedQuery<Course> typedQuery = em.createQuery(cq.select(courseRoot));
		List<Course> resultList = typedQuery.getResultList();
		logger.info("criteria query-->{}", resultList);
	}
	
	@Test
	public void left_join() {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Course> cq = cb.createQuery(Course.class);	
		Root<Course> courseRoot = cq.from(Course.class);
		Join<Object, Object> join = courseRoot.join("students",JoinType.LEFT);		
		TypedQuery<Course> typedQuery = em.createQuery(cq.select(courseRoot));
		List<Course> resultList = typedQuery.getResultList();
		logger.info("criteria query-->{}", resultList);
	}
}
