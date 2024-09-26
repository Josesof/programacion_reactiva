package com.microservicio_nombres_reactivo;

import com.microservicio_nombres_reactivo.controller.NamesController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import reactor.test.StepVerifier;

@SpringBootTest
class ApplicationTests {

	@Autowired
	NamesController namesController;

	@Test
	void contextLoads() {
		StepVerifier.create(namesController.getNames())
			.expectNext("one")
			.expectNext("two", "three")
			.expectNextCount(2)
			.verifyComplete();
	}

}
