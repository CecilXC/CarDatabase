package com.packt.cardatabase;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.packt.cardatabase.web.CarController;

@SpringBootTest
class CardatabaseApplicationTests {
	/*
	 * We use field injection here, which is well-suited for test classes because
	 * you will never instantiate your test classes directly. You can read more
	 * about dependency injection of test fixtures in the Spring documentation:
	 * https://docs.spring.io/spring-framework/reference/testing/testcontext-framework/fixture-di.html.
	 */
	@Autowired
	private CarController carController;

	@Test
	@DisplayName("First example test case")
	void contextLoads() {
		assertThat(carController).isNotNull();
	}

}
