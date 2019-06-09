package com.jitendra.jpa.hibernate.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
//@Entity
//@Inheritance(strategy=InheritanceType.JOINED)
public abstract class Employee {

	@Id
	@GeneratedValue
	private int id;

	@Column(name = "name", nullable = false)
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

	public Employee(String name) {
		super();
		this.name = name;
	}

	protected Employee() {
	}

	@Override
	public String toString() {
		return String.format("Employee[%s]", name);
	}
}
