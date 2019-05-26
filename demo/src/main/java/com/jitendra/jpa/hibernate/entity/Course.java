package com.jitendra.jpa.hibernate.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Course {

	@Override
	public String toString() {
		return String.format("Course[%s]",name);
	}

	@Id
	@GeneratedValue
	private int id;
	
	private String name;

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
	
}
