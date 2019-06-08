package com.jitendra.jpa.hibernate;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.jitendra.jpa.hibernate.entity.Course;
import com.jitendra.jpa.hibernate.entity.Student;
import com.jitendra.jpa.hibernate.repository.CourseRepository;
import com.jitendra.jpa.hibernate.repository.StudentRepository;

@SpringBootApplication
public class DemoApplication implements CommandLineRunner {

	private Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private StudentRepository studentRepository;
	
	@Autowired
	private CourseRepository courseRepository;

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		/*List<Review> reviews = new ArrayList<Review>();
		reviews.add(new Review("5", "Awesome Course"));
		reviews.add(new Review("5", "Amazing Course"));*/		
		//courseRepository.addReviewsForCourse(10002,reviews );
		//studentRepository.saveStudentWithPassport();
		//courseRepository.addHardCodedReviewsForCourse();
		studentRepository.insertHardCodedStudentAndCourse();
		studentRepository.insertStudentAndCourse(new Student("Tom"), new Course("Tom and Jerry Fight"));
	}

}
