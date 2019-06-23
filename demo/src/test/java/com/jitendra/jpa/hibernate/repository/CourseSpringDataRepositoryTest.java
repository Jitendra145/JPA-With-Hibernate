package com.jitendra.jpa.hibernate.repository;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.junit4.SpringRunner;

import com.jitendra.jpa.hibernate.DemoApplication;
import com.jitendra.jpa.hibernate.entity.Course;

@RunWith(SpringRunner.class)
@SpringBootTest(classes=DemoApplication.class)
public class CourseSpringDataRepositoryTest {

	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private CourseSpringDataRepository repository;
	
	@Test
	public void findById_Course_Present() {
		Optional<Course> courseOptional = repository.findById(10001);
		assertTrue(courseOptional.isPresent());
		logger.info("is Present-->{}",courseOptional.isPresent());
	}

	@Test
	public void findById_Course_Not_Present() {
		Optional<Course> courseOptional = repository.findById(20001);
		assertFalse(courseOptional.isPresent());
		logger.info("is Present-->{}",courseOptional.isPresent());
	}
	
	@Test
	public void crud_test() {
		/*
		 * Course course = new Course("React JS"); repository.save(course);
		 * course.setName("React JS-updated"); repository.save(course);
		 */
		logger.info("Course-->{}",repository.findAll());
		logger.info("Count-->{}",repository.count());
	}
	
	@Test
	public void sort() {
		Sort sort = new Sort(Sort.Direction.DESC,"name");
		logger.info("Sorted Courses-->{}",repository.findAll(sort));
	}
	
	@Test
	public void pagination() {
		PageRequest pageRequest = PageRequest.of(0, 3);
		Page<Course> firstPage = repository.findAll(pageRequest);
		logger.info("First Page Courses-->{}",firstPage.getContent());
		Pageable secondPageable = firstPage.nextPageable();
		Page<Course> secondPage = repository.findAll(secondPageable);
		logger.info("Second Page Courses-->{}",secondPage.getContent());
		
		Pageable thirdPageable = secondPage.nextPageable();
		Page<Course> thirdPage = repository.findAll(thirdPageable);
		logger.info("Third Page Courses-->{}",thirdPage.getContent());
	}
	
	@Test
	public void custom_method() {
		logger.info("Named Query-->{}",repository.courseWith100StepsWithNamedQuery());
	}
}
