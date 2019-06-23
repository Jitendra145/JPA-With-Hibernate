package com.jitendra.jpa.hibernate.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.jitendra.jpa.hibernate.DemoApplication;
import com.jitendra.jpa.hibernate.entity.Course;
import com.jitendra.jpa.hibernate.entity.Student;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = DemoApplication.class)
public class CourseJPQLTest {

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	EntityManager em;

	@Test
	public void jpql_basic() {
		List resultList = em.createNamedQuery("fetch_all_courses").getResultList();
		logger.info("select c from Course c --> {}", resultList);
	}

	@Test
	public void jpql_typed() {
		TypedQuery<Course> typedQuery = em.createNamedQuery("fetch_all_courses", Course.class);
		List<Course> resultList = typedQuery.getResultList();
		logger.info("select c from Course c Typed --> {}", resultList);
	}

	@Test
	public void jpql_where() {
		TypedQuery<Course> typedQuery = em.createNamedQuery("fetch_all_courses_with_100", Course.class);
		List<Course> resultList = typedQuery.getResultList();
		logger.info("select c from Course c where name like '%100%'--> {}", resultList);
	}

	@Test
	public void jpql_courses_without_students() {
		TypedQuery<Course> typedQuery = em.createQuery("select c from Course c where c.students is empty",
				Course.class);
		List<Course> resultList = typedQuery.getResultList();
		logger.info("select c from Course c where c.students is empty--> {}", resultList);
	}

	@Test
	public void jpql_courses_withgreaterthan_or_equalto2_students() {
		TypedQuery<Course> typedQuery = em.createQuery("select c from Course c where size(c.students) >=2",
				Course.class);
		List<Course> resultList = typedQuery.getResultList();
		logger.info("select c from Course c where size(c.students) >=2--> {}", resultList);
	}

	@Test
	public void jpql_courses_orderby_students() {
		TypedQuery<Course> typedQuery = em.createQuery("select c from Course c order by size(c.students)",
				Course.class);
		List<Course> resultList = typedQuery.getResultList();
		logger.info("select c from Course c order by size(c.students)--> {}", resultList);
	}

	@Test
	public void jpql_number_student_in_certain_order() {
		TypedQuery<Student> typedQuery = em.createQuery("select s from Student s where s.passport.number like '%1234%'",
				Student.class);
		List<Student> resultList = typedQuery.getResultList();
		logger.info("select s from Student s where s.passport.number like '%1234%'--> {}", resultList);
	}

	@Test
	public void join() {
		Query query = em.createQuery("select c,s from Course c join c.students s");
		List<Object[]> resultList = query.getResultList();
		logger.info("Result size--> {}", resultList.size());
		for (Object[] result : resultList) {

			logger.info("Course--> {} Student-->{}", result[0], result[1]);
			// result[0]//Course
			// result[1];//Student
		}
	}
	@Test
	public void left_join() {
		Query query = em.createQuery("select c,s from Course c left join c.students s");
		List<Object[]> resultList = query.getResultList();
		logger.info("Result size--> {}", resultList.size());
		for (Object[] result : resultList) {

			logger.info("left join Course--> {} Student-->{}", result[0], result[1]);
			// result[0]//Course
			// result[1];//Student
		}
	}
	
	@Test
	public void cross_join() {
		Query query = em.createQuery("select c,s from Course c,Student s");
		List<Object[]> resultList = query.getResultList();
		logger.info("Result size--> {}", resultList.size());
		for (Object[] result : resultList) {

			logger.info("cross join Course--> {} Student-->{}", result[0], result[1]);
			// result[0]//Course
			// result[1];//Student
		}
	}
}
