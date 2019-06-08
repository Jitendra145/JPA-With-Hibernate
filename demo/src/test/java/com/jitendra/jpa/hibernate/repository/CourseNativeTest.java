package com.jitendra.jpa.hibernate.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.transaction.Transactional;

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
public class CourseNativeTest {

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	EntityManager em;

	@Test
	public void native_query_basic() {
		Query query = em.createNativeQuery("select * from Course", Course.class);
		List resultList = query.getResultList();
		logger.info("select * from Course --> {}", resultList);
	}

	@Test
	public void native_query_with_parameter() {
		Query query = em.createNativeQuery("select * from Course where id = ?", Course.class);
		query.setParameter(1, 10000);
		List resultList = query.getResultList();
		logger.info("select * from Course where id = 1000 --> {}", resultList);
	}

	@Test
	public void native_query_with_named_parameter() {
		Query query = em.createNativeQuery("select * from Course where id = :id", Course.class);
		query.setParameter("id", 10000);
		List resultList = query.getResultList();
		logger.info("select * from Course where id = 1000 named  --> {}", resultList);
	}

	@Test
	@Transactional
	public void native_query_to_update() {
		Query query = em.createNativeQuery("update Course set last_updated_date=sysdate()", Course.class);
		int noOfRows = query.executeUpdate();
		logger.info("update Course set last_updated_date=sysdate() --> {}",noOfRows);
	}
}
