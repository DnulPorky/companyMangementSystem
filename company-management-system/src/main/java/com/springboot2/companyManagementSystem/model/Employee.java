package com.springboot2.companyManagementSystem.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
/**
Entities in JPA are nothing but POJOs representing data that can be persisted to the database. 
An entity represents a table stored in a database. 
Every instance of an entity represents a row in the table.
The @Table annotation allows you to specify the details of the table(four attributes: name, catalogue, 
schema, and enforce unique constraints on columns)
*/
@Entity
@Table(name = "employees")
public class Employee {
	
    private long id;
    private String firstName;
    private String lastName;
    private String emailId;

    private Company company;
    
	/**
     * An empty constructor is needed to create a new instance via reflection by your persistence framework. 
     * If we didn't provide any additional constructors with arguments for the class, 
     * we didn't need to provide an empty constructor because we get one per default.
     */
    public Employee() {

    }

    public Employee(String firstName, String lastName, String emailId) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.emailId = emailId;
    }
    
    /**
     * @Id - indicating the member field below is the primary key of current entity
     * @GeneratedValue - used to configure the 'strategy' of increment of the specified column(field).
     */
  	@Id
  	@GeneratedValue(strategy = GenerationType.AUTO)
    public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	/**
	 * @Column - used to customize mapping between entity attribute and db column
	 */
	@Column(name = "first_name", nullable = false)
	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	
	@Column(name = "last_name", nullable = false)
	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	@Column(name = "email_address", nullable = false)
	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	@ManyToOne
	@JoinColumn(name="company_id", nullable = false)
	public Company getCompany() {
		return company;
	}

	public void setCompany(Company company) {
		this.company = company;
	}

}