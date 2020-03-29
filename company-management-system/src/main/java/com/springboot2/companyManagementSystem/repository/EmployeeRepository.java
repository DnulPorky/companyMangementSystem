package com.springboot2.companyManagementSystem.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.springboot2.companyManagementSystem.model.Employee;



/**
 * @Repository annotation is used to indicate that the class provides the mechanism for storage, retrieval, 
 * search, update and delete operation on objects.
 * @Repository annotation is a specialization of @Component annotation, 
 * so Spring Repository classes are autodetected by spring framework through classpath scanning.
 */
@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

}
