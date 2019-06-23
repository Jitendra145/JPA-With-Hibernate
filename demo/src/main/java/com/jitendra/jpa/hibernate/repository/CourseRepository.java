package com.jitendra.jpa.hibernate.repository;

import java.util.List;

import javax.persistence.EntityManager;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.jitendra.jpa.hibernate.entity.Course;
import com.jitendra.jpa.hibernate.entity.Review;
import com.jitendra.jpa.hibernate.entity.ReviewRating;

@Repository
@Transactional
public class CourseRepository {

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	EntityManager em;

	public Course findById(int id) {
		return em.find(Course.class, id);
	}
	
	public Review findReviewById(int id) {
		return em.find(Review.class, id);
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

	/**
	 * 
	 */
	public void addHardCodedReviewsForCourse() {
		// retrieve the record for 10002
		Course course = findById(10002);
		logger.info("All reviews-->{}",course.getReviews());
		// add 2 reviews,
		Review review1 = new Review(ReviewRating.FIVE, "Awesome Course");
		course.addReview(review1);
		review1.setCourse(course);
		
		Review review2 = new Review(ReviewRating.FIVE, "Amazing Course");
		course.addReview(review2);
		review2.setCourse(course);
		
		// save it into DB
		em.persist(review1);
		em.persist(review2);
	}
	
	public void addReviewsForCourse(int courseId,List<Review> reviews) {
		Course course = findById(courseId);
		logger.info("All reviews-->{}",course.getReviews());
		for(Review review: reviews) {
			course.addReview(review);
			review.setCourse(course);
			em.persist(review);
		}
	}
}
