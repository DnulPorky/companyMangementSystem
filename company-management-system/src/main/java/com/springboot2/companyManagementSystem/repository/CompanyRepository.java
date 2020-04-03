package com.springboot2.companyManagementSystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.springboot2.companyManagementSystem.model.Company;

@Repository
public interface CompanyRepository extends JpaRepository<Company, Long> {

}
