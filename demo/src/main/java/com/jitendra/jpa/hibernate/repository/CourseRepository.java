package com.jitendra.jpa.hibernate.repository;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.jitendra.jpa.hibernate.entity.Course;

@Repository
public class CourseRepository {

	@Autowired
	EntityManager em;
	
	public Course findById(int id) {
		return em.find(Course.class, id);
	}
}
