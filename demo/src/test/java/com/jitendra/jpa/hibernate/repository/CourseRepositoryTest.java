package com.jitendra.jpa.hibernate.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

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
import com.jitendra.jpa.hibernate.entity.Review;

@RunWith(SpringRunner.class)
@SpringBootTest(classes=DemoApplication.class)
public class CourseRepositoryTest {

	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private CourseRepository repository;

	private Course course;
	
	@Test
	@DirtiesContext
	public void playWithEntityManager_basic(){
		repository.playWithEntityManager();
	}
	
	@Test
	@Transactional
	public void retieveReviewsForCourse(){
		Course course = repository.findById(10000);
		logger.info("{}",course.getReviews());
	}
	
	@Test
	@Transactional
	public void retieveCourseForReviews(){
		Review review = repository.findReviewById(50000);
		logger.info("{}",review.getCourse());
	}
}
