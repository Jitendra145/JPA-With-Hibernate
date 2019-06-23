package com.jitendra.jpa.hibernate.entity;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Review {

	@Override
	public String toString() {
		return String.format("Review[%s %s]",rating, description);
	}

	@Id
	@GeneratedValue
	private int id;

	@Enumerated(EnumType.STRING)
	private ReviewRating rating;
	
	private String description;
	
	@ManyToOne
	private Course course;

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getId() {
		return id;
	}

	public Review(ReviewRating rating,String description) {
		super();
		this.rating = rating;
		this.description = description;
	}

	protected Review() {
	}

	public ReviewRating getRating() {
		return rating;
	}

	public void setRating(ReviewRating rating) {
		this.rating = rating;
	}

	public Course getCourse() {
		return course;
	}

	public void setCourse(Course course) {
		this.course = course;
	}

}
