package com.jitendra.jpa.hibernate.repository;

import javax.persistence.EntityManager;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.jitendra.jpa.hibernate.entity.Course;
import com.jitendra.jpa.hibernate.entity.Passport;
import com.jitendra.jpa.hibernate.entity.Student;

@Repository
@Transactional
public class StudentRepository {

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	EntityManager em;

	public Student findById(int id) {
		return em.find(Student.class, id);
	}

	public void deleteById(int id) {
		Student student = findById(id);
		em.remove(student);
	}

	public Student save(Student student) {
		if (student.getId() == 0) {
			em.persist(student);
		} else {
			em.merge(student);
		}
		return student;
	}

	public void saveStudentWithPassport() {
		Passport passport = new Passport("P767541");
		em.persist(passport);
		Student student = new Student("Mike");
		student.setPassport(passport);
		em.persist(student);
	}

	public void extracted() {
		// DB Operation 1 - retrieve Student
		Student student = em.find(Student.class, 20001);
		// Persistence Context -->(Student)

		// DB Operation 2 - retrieve Passport
		Passport passport = student.getPassport();
		// Persistence Context -->(Student,Passport)

		// DB Operation 3 - update Passport
		passport.setNumber("E123457");
		// Persistence Context -->(Student,Passport++)

		// DB Operation 4 - update Student
		student.setName("Mike--Updated");
		// Persistence Context -->(Student++,Passport++)
	}

	public void insertHardCodedStudentAndCourse() {
		Student student = new Student("Jerry");
		Course course = new Course("React JS");
		em.persist(student);
		em.persist(course);

		student.addCourse(course);
		course.addStudent(student);

		em.persist(student);
	}

	public void insertStudentAndCourse(Student student, Course course) {
		// Student student = new Student("Jerry");
		// Course course = new Course("React JS");
		student.addCourse(course);
		course.addStudent(student);

		em.persist(student);
		em.persist(course);
	}
}
