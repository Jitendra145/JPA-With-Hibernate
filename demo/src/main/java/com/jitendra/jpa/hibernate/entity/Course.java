package com.jitendra.jpa.hibernate.entity;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Cacheable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.PreRemove;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.annotations.Where;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@NamedQueries(value = { @NamedQuery(name = "fetch_all_courses", query = "select c from Course c"),
		@NamedQuery(name = "fetch_all_courses_join_fetch", query = "select c from Course c JOIN FETCH c.students s"),
		@NamedQuery(name = "fetch_all_courses_with_100", query = "select c from Course c where name like '%100%'") })

@Cacheable
@SQLDelete(sql="update course set is_deleted=true where id=?")
@Where(clause="is_deleted=false")
public class Course {

	private static Logger LOGGER = LoggerFactory.getLogger(Course.class);
	
	@Override
	public String toString() {
		return String.format("Course[%s]", name);
	}

	@Id
	@GeneratedValue
	private int id;

	@Column(name = "name", nullable = false)
	private String name;

	@OneToMany(mappedBy="course") //with respect to Course; course is the Owner of the relationship
	private List<Review> reviews = new ArrayList<>();
	
	@ManyToMany(mappedBy="courses")//student is the owning side of the relatonship
	@JsonIgnore
	private List<Student> students = new ArrayList<>();
	
	@UpdateTimestamp
	private LocalDateTime lastUpdatedDate;
	@CreationTimestamp
	private LocalDateTime createdDate;
	
	private boolean isDeleted;

	public boolean isDeleted() {
		return isDeleted;
	}

	public void setDeleted(boolean isDeleted) {
		this.isDeleted = isDeleted;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getId() {
		return id;
	}

	public Course(String name) {
		super();
		this.name = name;
	}

	protected Course() {
	}

	public List<Review> getReviews() {
		return reviews;
	}

	public void addReview(Review review) {
		this.reviews.add(review);
	}
	
	public void removeReview(Review review) {
		this.reviews.remove(review);
	}

	public List<Student> getStudents() {
		return students;
	}

	public void addStudent(Student student) {
		this.students.add(student);
	}
	
	@PreRemove
	public void PreRemove() {
		LOGGER.info("Setting isDeleted to true");
		this.isDeleted = true;
	}
	
}
