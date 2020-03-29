package com.springboot2.companyManagementSystem;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @SpringBootApplication is a composite annotation that combines three other annotations:
 *  - @SprinbBootConfiguration - designate this class as a configuration class 
 *  - @EnableAutoConfiguration - tells Spring Boot to automatically configure any components 
 *  	that it thinks you'll need
 *  - @ComponentScanning - enables Spring to automatically scan, discover and register as components 
 *  	of Spring application context, those classes that have @Component or derived annotations(@Controller, @Service, etc)) 
 */
@SpringBootApplication
public class CompanyManagementSystemApplication {

	public static void main(String[] args) {
		SpringApplication.run(CompanyManagementSystemApplication.class, args);
	}

}
