package com.jitendra.jpa.hibernate.repository;

import javax.persistence.EntityManager;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.jitendra.jpa.hibernate.entity.Course;

@Repository
@Transactional
public class CourseRepository {

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	EntityManager em;

	public Course findById(int id) {
		return em.find(Course.class, id);
	}

	public void deleteById(int id) {
		Course course = findById(id);
		em.remove(course);
	}

	public Course save(Course course) {
		if (course.getId() == 0) {
			em.persist(course);
		} else {
			em.merge(course);
		}
		return course;
	}

	public void playWithEntityManager() {
		logger.info("Play With Entity Manager --> Start");
		Course course1 = new Course("Web Services in 100 steps");
		em.persist(course1);
		Course course2 = findById(10001);
		course2.setName("JPA with Hibernate -- Updated");
		em.persist(course2);
	}
}
