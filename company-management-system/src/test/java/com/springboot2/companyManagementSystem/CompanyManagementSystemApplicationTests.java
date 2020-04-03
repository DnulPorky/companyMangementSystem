package com.springboot2.companyManagementSystem;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @RunWith is a JUnit annotation, providing a test runner that guides JUnit in
 *          running a test. SpringRunner is a Spring-provided test runner that
 *          provides for the creation of a Spring application context that the
 *          test will run against
 * @SpringBootTest tells JUnit to bootstrap the test with Spring Boot
 *                 capabilities. A test class equivalent of calling
 *                 Spring-Application.run() in a main() method
 *
 */
//@RunWith(SpringRunner.class)
@SpringBootTest
class CompanyManagementSystemApplicationTests {

	@Test
	void contextLoads() {
	}

}
