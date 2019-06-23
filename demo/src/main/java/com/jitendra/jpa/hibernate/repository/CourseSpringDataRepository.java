package com.jitendra.jpa.hibernate.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.jitendra.jpa.hibernate.entity.Course;

@RepositoryRestResource(path="courses")
public interface CourseSpringDataRepository extends JpaRepository<Course,Integer> {

	List<Course> findByName(String name);
	
	@Query("select c from Course c where name like '%100%'")
	List<Course> courseWith100Steps();	
	
	@Query(value="select * from Course c where name like '%100%'",nativeQuery=true)
	List<Course> courseWith100StepsWithNativeQuery();	
	
	@Query(name="fetch_all_courses_with_100")
	List<Course> courseWith100StepsWithNamedQuery();	
	
	
}
