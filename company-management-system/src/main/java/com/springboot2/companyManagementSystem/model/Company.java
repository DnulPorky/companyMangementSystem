package com.springboot2.companyManagementSystem.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "companies")
public class Company {

	@Id
//	@GeneratedValue(strategy = GenerationType.AUTO)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	@NotNull
	private String name;

	@OneToMany(targetEntity = Employee.class, mappedBy = "company", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private Set<Employee> employees = new HashSet<>();

	public Company() {
	}

	public Company(String name) {
		this.name = name;
	}

	public long getCompany_id() {
		return id;
	}

	public void setCompany_id(long company_id) {
		this.id = company_id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Set<Employee> getEmployees() {
		return employees;
	}

	public void setEmployees(Set<Employee> employees) {
		this.employees = employees;
	}
}
