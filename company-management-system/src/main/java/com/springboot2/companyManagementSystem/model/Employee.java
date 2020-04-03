package com.springboot2.companyManagementSystem.model;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * Entities in JPA are nothing but POJOs representing data that can be persisted
 * to the database. An entity represents a table stored in a database. Every
 * instance of an entity represents a row in the table. The @Table annotation
 * allows you to specify the details of the table(four attributes: name,
 * catalogue, schema, and enforce unique constraints on columns)
 */
@Entity
@Table(name = "employees")
public class Employee {

	/**
	 * @Id - indicating the member field below is the primary key of current entity
	 * @GeneratedValue - used to configure the 'strategy' of increment of the
	 *                 specified column(field).
	 */
	@Id
//  	@GeneratedValue(strategy = GenerationType.AUTO)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	/**
	 * @Column - used to customize mapping between entity attribute and db column
	 */
	@Column(name = "first_name", nullable = false)
	private String firstName;

	@Column(name = "last_name", nullable = false)
	private String lastName;

	@Column(name = "email_address", nullable = false)
	private String emailId;
	
	@Column(name = "employment_date", nullable= false)
//	@Temporal(TemporalType.DATE)
	@JsonFormat(pattern = "dd-MM-yyyy")
	private LocalDate employmentDate;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "company_id", nullable = false)
	@JsonIgnore
	private Company company;

	/**
	 * An empty constructor is needed to create a new instance via reflection by
	 * your persistence framework. If we didn't provide any additional constructors
	 * with arguments for the class, we didn't need to provide an empty constructor
	 * because we get one per default.
	 */
	public Employee() {

	}

	public Employee(String firstName, String lastName, String emailId) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.emailId = emailId;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	public Company getCompany() {
		return company;
	}

	public void setCompany(Company company) {
		this.company = company;
	}

	public LocalDate getEmploymentDate() {
		return employmentDate;
	}

	public void setEmploymentDate(LocalDate employmentDate) {
		this.employmentDate = employmentDate;
	}

}