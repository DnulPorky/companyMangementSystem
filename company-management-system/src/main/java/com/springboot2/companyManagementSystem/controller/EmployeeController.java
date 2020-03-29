package com.springboot2.companyManagementSystem.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springboot2.companyManagementSystem.exception.ResourceNotFoundException;
import com.springboot2.companyManagementSystem.model.Employee;
import com.springboot2.companyManagementSystem.repository.EmployeeRepository;


/**
 * It's a convenience annotation that combines @Controller and @ResponseBody – which eliminates 
 * the need to annotate every request handling method of the controller class with the @ResponseBody annotation.
 * Every request handling method of the controller class automatically serializes return objects into HttpResponse.
 * @Controller is simply a specialization of the @Component class and 
 * allows implementation classes to be autodetected through the classpath scanning.
 * A controller is a class that handles request and responds with informations of some sort(Spring MVC framework concept)
 */
@RestController
@RequestMapping("api/employee")
public class EmployeeController {
	/**
	 * @Autowired annotation allows Spring to resolve and inject collaborating beans into your bean.
	 */
	@Autowired
	EmployeeRepository employeeRepository;
	
	@GetMapping("/employees")
	public List<Employee> findAll() {
		return employeeRepository.findAll();
	}
	
	/**
	 * @ResponseEntity represents the whole HTTP response: status code, headers, and body. 
	 * We can use it to fully configure the HTTP response.
	 * @PathVariable is a Spring annotation which indicates that a method parameter should 
	 * be bound to a URI template variable.
	 */
	@GetMapping("/employees/{id}")
	public ResponseEntity<Employee> getEmployeeById(@PathVariable("id") Long employeeId) throws ResourceNotFoundException {
		Employee employee = employeeRepository.findById(employeeId).orElseThrow(() -> new ResourceNotFoundException("Employee not found for this id :: " + employeeId));
		return ResponseEntity.ok().body(employee);
	}
	
	@PostMapping("/employees")
	public Employee createEmployee(@Valid @RequestBody Employee employee) {
		return employeeRepository.save(employee);
	}
	/**
	 * @RequestBody indicate a method parameter should be bound to the body of the HTTP request. 
	 * @ResponseBody annotation can be put on a method and indicates that the return type should 
	 * be written straight to the HTTP response body (and not placed in a Model, or interpreted as a view name).
	 */
	@PutMapping("/employees/{id}")
	public ResponseEntity<Employee> updateEmployee(@PathVariable("id") Long employeeId, @Valid @RequestBody Employee employeeDetails) throws ResourceNotFoundException {
		Employee employee = employeeRepository.findById(employeeId).orElseThrow(() -> new ResourceNotFoundException("Employee not found for this id :: " + employeeId));
		employee.setFirstName(employeeDetails.getFirstName());
		employee.setLastName(employeeDetails.getLastName());
		employee.setEmailId(employeeDetails.getEmailId());
		final Employee updatedEmployee = employeeRepository.save(employee);
		return ResponseEntity.ok().body(updatedEmployee);
	}
	@DeleteMapping("/employees/{id}")
	public Map<String, Boolean> deleteEmployee(@PathVariable("id") Long employeeId) throws ResourceNotFoundException {
		Employee employee = employeeRepository.findById(employeeId).orElseThrow(() -> new ResourceNotFoundException("Employee not found for this id :: " + employeeId));
		employeeRepository.delete(employee);
		Map<String, Boolean> response = new HashMap<String, Boolean>();
		response.put("deleted", Boolean.TRUE);
		return response;
	}
}