package com.jitendra.jpa.hibernate.repository;

import javax.persistence.EntityManager;
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
import com.jitendra.jpa.hibernate.entity.Passport;
import com.jitendra.jpa.hibernate.entity.Student;

@RunWith(SpringRunner.class)
@SpringBootTest(classes=DemoApplication.class)
public class StudentRepositoryTest {

	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private StudentRepository repository;
	
	@Autowired
	EntityManager em;
	
	@Test
	//@Transactional ---> after commenting transaction support is provided by Student Repository
	public void someTest() {
		repository.extracted();
	}

	@Test
	@Transactional
	public void retriveStudentAndPassportDetails() {
		Student student = em.find(Student.class,20001);
		logger.info("Student-->{}",student);
		logger.info("Passport-->{}",student.getPassport());
	}
	
	@Test
	@Transactional
	public void retrivePassportAndAssociatedStudentDetails() {
		Passport passport = em.find(Passport.class,40001);
		logger.info("Passport-->{}",passport);
		logger.info("Student-->{}",passport.getStudent());
	}
	
	/*@Test
	@DirtiesContext
	public void saveStudentWithPassport_basic(){
		repository.saveStudentWithPassport();
	}*/
	
	@Test
	@Transactional
	public void retriveStudentAndCourse() {
		Student student = em.find(Student.class, 20001);
		logger.info("Student-->{}",student);
		logger.info("Courses-->{}",student.getCourses());
	}
	
	@Test
	@Transactional
	public void retriveCourseAndStudent() {
		Course course = em.find(Course.class, 10000);
		logger.info("Course-->{}",course);
		logger.info("Students-->{}",course.getStudents());
	}
}
