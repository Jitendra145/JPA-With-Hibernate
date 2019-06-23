package com.jitendra.jpa.hibernate.repository;

import static org.junit.Assert.assertNull;

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
public class CourseRepositoryTest {

	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private CourseRepository repository;

	private Course course;
	
	/*@Test
	@Transactional
	public void findById_First_Level_Cache() {
		Course course1 = repository.findById(10001);
		logger.info("First course retrieved-->{}",course1);
		Course course2 = repository.findById(10001);
		logger.info("First course retrieved again-->{}",course2);
		assertEquals("SpringBoot with Hibernate", course1.getName());
		assertEquals("SpringBoot with Hibernate", course2.getName());
	}*/
	
	@Test
	@DirtiesContext
	public void deleteById_basic(){		
		repository.deleteById(10001);
		assertNull(repository.findById(10001));
	}
	
	
	/*@Test
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
		Review review = repository.findReviewById(10000);
		logger.info("{}",review.getCourse());
	}*/
}
