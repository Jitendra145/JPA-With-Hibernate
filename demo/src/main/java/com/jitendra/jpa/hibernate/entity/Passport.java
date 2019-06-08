package com.jitendra.jpa.hibernate.entity;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

@Entity
public class Passport {

	@Override
	public String toString() {
		return String.format("Passport[%s]", number);
	}

	@Id
	@GeneratedValue
	private int id;

	@Column(name = "number", nullable = false)
	private String number;
	
	@OneToOne(fetch = FetchType.LAZY,mappedBy="passport")//student is owning side of passport
	private Student student;

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public int getId() {
		return id;
	}

	public Passport(String number) {
		super();
		this.number = number;
	}

	protected Passport() {
	}

	public Student getStudent() {
		return student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}

}
