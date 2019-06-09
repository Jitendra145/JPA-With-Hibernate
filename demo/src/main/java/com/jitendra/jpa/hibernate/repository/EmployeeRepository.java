package com.jitendra.jpa.hibernate.repository;

import java.util.List;

import javax.persistence.EntityManager;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.jitendra.jpa.hibernate.entity.Employee;
import com.jitendra.jpa.hibernate.entity.FullTimeEmployee;
import com.jitendra.jpa.hibernate.entity.PartTimeEmployee;

@Repository
@Transactional
public class EmployeeRepository {

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	EntityManager em;

	// insert employee
	public void insert(Employee employee) {
		em.persist(employee);
	}

	public List<Employee> findAllEmployee() {
		return em.createQuery("select emp from Employee emp", Employee.class).getResultList();
	}

	public List<PartTimeEmployee> findAllPartTimeEmployee() {
		return em.createQuery("select emp from PartTimeEmployee emp", PartTimeEmployee.class).getResultList();
	}
	
	public List<FullTimeEmployee> findAllFullTimeEmployee() {
		return em.createQuery("select emp from FullTimeEmployee emp", FullTimeEmployee.class).getResultList();
	}

}
