package com.springboot2.companyManagementSystem.controller;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
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
import com.springboot2.companyManagementSystem.model.Company;
import com.springboot2.companyManagementSystem.model.Employee;
import com.springboot2.companyManagementSystem.repository.CompanyRepository;
import com.springboot2.companyManagementSystem.repository.EmployeeRepository;

/**
 * It's a convenience annotation that combines @Controller and @ResponseBody –
 * which eliminates the need to annotate every request handling method of the
 * controller class with the @ResponseBody annotation. Every request handling
 * method of the controller class automatically serializes return objects into
 * HttpResponse.
 * 
 * @Controller is simply a specialization of the @Component class and allows
 *             implementation classes to be autodetected through the classpath
 *             scanning. A controller is a class that handles request and
 *             responds with informations of some sort(Spring MVC framework
 *             concept)
 */
@RestController
@RequestMapping("api/employee")
public class EmployeeController {
	/**
	 * @Autowired annotation allows Spring to resolve and inject collaborating beans
	 *            into your bean.
	 */
	@Autowired
	private EmployeeRepository employeeRepository;

	@Autowired
	private CompanyRepository companyRepository;

	@GetMapping("/employees")
	public List<Employee> findAll() {
		return employeeRepository.findAll();
	}

	/**
	 * @ResponseEntity represents the whole HTTP response: status code, headers, and
	 *                 body. We can use it to fully configure the HTTP response.
	 * @PathVariable is a Spring annotation which indicates that a method parameter
	 *               should be bound to a URI template variable.
	 */
	@GetMapping("/employees/{id}")
	public ResponseEntity<Employee> getEmployeeById(@PathVariable("id") Long employeeId)
			throws ResourceNotFoundException {
		Employee employee = employeeRepository.findById(employeeId)
				.orElseThrow(() -> new ResourceNotFoundException("Employee not found for this id :: " + employeeId));
		return ResponseEntity.ok().body(employee);
	}

	@GetMapping("/company/{companyId}/employees")
	public List<Employee> getAllEmployeesByCompanyId(@PathVariable(value = "companyId") Long companyId) {
		return employeeRepository.findByCompanyId(companyId);
	}

	@GetMapping("/employees/date/{employmentDate}")
	public List<Employee> getAllEmployeesByEmploymentDate(
			@PathVariable(value = "employmentDate") @DateTimeFormat(pattern = "dd-MM-yyyy") LocalDate employmentDate)
			throws ResourceNotFoundException {

		return employeeRepository.findByEmploymentDate(employmentDate);
	}

	@GetMapping("/employees/between/{startDate}/{endDate}")
	public List<Employee> getAllEmployeesByEmploymentDateBetween(
			@PathVariable(value = "startDate") @DateTimeFormat(pattern = "dd-MM-yyyy") LocalDate startDate,
			@PathVariable(value = "endDate") @DateTimeFormat(pattern = "dd-MM-yyyy") LocalDate endDate)
			throws ResourceNotFoundException {

		return employeeRepository.findAllByEmploymentDateBetween(startDate, endDate);
	}

	@PostMapping("/company/{companyId}/employee")
	public Employee createEmployee(@PathVariable(value = "companyId") Long companyId,
			@Valid @RequestBody Employee employee) throws ResourceNotFoundException {
		Company company = companyRepository.findById(companyId)
				.orElseThrow(() -> new ResourceNotFoundException("Company not found for this id :: " + companyId));
		employee.setCompany(company);
		return employeeRepository.save(employee);
	}

	/**
	 * @RequestBody indicate a method parameter should be bound to the body of the
	 *              HTTP request.
	 * @ResponseBody annotation can be put on a method and indicates that the return
	 *               type should be written straight to the HTTP response body (and
	 *               not placed in a Model, or interpreted as a view name).
	 */
	@PutMapping("/companies/{companyId}/employees/{employeeId}")
	public ResponseEntity<Employee> updateEmployee(@PathVariable(value = "companyId") Long companyId,
			@PathVariable(value = "employeeId") Long employeeId, @Valid @RequestBody Employee employeeDetails)
			throws ResourceNotFoundException {
		if (!companyRepository.existsById(companyId)) {
			throw new ResourceNotFoundException("CompanyId " + companyId + " not found");
		}
		Employee employee = employeeRepository.findByIdAndCompanyId(employeeId, companyId)
				.orElseThrow(() -> new ResourceNotFoundException(
						"Employee not found with id " + employeeId + " and companyId " + companyId));
		employee.setFirstName(employeeDetails.getFirstName());
		employee.setLastName(employeeDetails.getLastName());
		employee.setEmailId(employeeDetails.getEmailId());
		final Employee updatedEmployee = employeeRepository.save(employee);
		return ResponseEntity.ok().body(updatedEmployee);
	}

	@DeleteMapping("/companies/{companyId}/employees/{employeeId}")
	public Map<String, Boolean> deleteComment(@PathVariable(value = "companyId") Long companyId,
			@PathVariable(value = "employeeId") Long employeeId) throws ResourceNotFoundException {
		Employee employee = employeeRepository.findByIdAndCompanyId(employeeId, companyId)
				.orElseThrow(() -> new ResourceNotFoundException(
						"Employee not found with id " + employeeId + " and companyId " + companyId));
		employeeRepository.delete(employee);
		Map<String, Boolean> response = new HashMap<String, Boolean>();
		response.put("deleted", Boolean.TRUE);
		return response;
	}
}
