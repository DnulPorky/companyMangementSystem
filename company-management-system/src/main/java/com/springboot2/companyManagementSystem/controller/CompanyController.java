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
import com.springboot2.companyManagementSystem.model.Company;
import com.springboot2.companyManagementSystem.repository.CompanyRepository;

@RestController
@RequestMapping("api/company")
public class CompanyController {
	/**
	 * @Autowired annotation allows Spring to resolve and inject collaborating beans
	 *            into your bean.
	 */
	@Autowired
	private CompanyRepository companyRepository;

	@GetMapping("/companies")
	public List<Company> findAll() {
		return companyRepository.findAll();
	}

	/**
	 * @ResponseEntity represents the whole HTTP response: status code, headers, and
	 *                 body. We can use it to fully configure the HTTP response.
	 * @PathVariable is a Spring annotation which indicates that a method parameter
	 *               should be bound to a URI template variable.
	 */
	@GetMapping("/companies/{id}")
	public ResponseEntity<Company> getCompanyById(@PathVariable("id") Long companyId) throws ResourceNotFoundException {
		Company company = companyRepository.findById(companyId)
				.orElseThrow(() -> new ResourceNotFoundException("Company not found for this id :: " + companyId));
		return ResponseEntity.ok().body(company);
	}

	@PostMapping("/companies")
	public Company createCompany(@Valid @RequestBody Company company) {
		return companyRepository.save(company);
	}

	/**
	 * @RequestBody indicate a method parameter should be bound to the body of the
	 *              HTTP request.
	 * @ResponseBody annotation can be put on a method and indicates that the return
	 *               type should be written straight to the HTTP response body (and
	 *               not placed in a Model, or interpreted as a view name).
	 */
	@PutMapping("/companies/{id}")
	public ResponseEntity<Company> updateCompany(@PathVariable("id") Long companyId,
			@Valid @RequestBody Company companyDetails) throws ResourceNotFoundException {
		Company company = companyRepository.findById(companyId)
				.orElseThrow(() -> new ResourceNotFoundException("Company not found for this id :: " + companyId));
		company.setName(companyDetails.getName());
		final Company updatedCompany = companyRepository.save(company);
		return ResponseEntity.ok().body(updatedCompany);
	}

	@DeleteMapping("/companies/{id}")
	public Map<String, Boolean> deleteCompany(@PathVariable("id") Long companyId) throws ResourceNotFoundException {
		Company company = companyRepository.findById(companyId)
				.orElseThrow(() -> new ResourceNotFoundException("Company not found for this id :: " + companyId));
		companyRepository.delete(company);
		Map<String, Boolean> response = new HashMap<String, Boolean>();
		response.put("deleted", Boolean.TRUE);
		return response;
	}
}
