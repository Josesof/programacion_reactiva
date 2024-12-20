package com.microservicio_nombres_reactivo;

import com.microservicio_nombres_reactivo.controller.NamesController;
import com.microservicio_nombres_reactivo.service.NamesServiceImpl;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import reactor.test.StepVerifier;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@SpringBootTest
class ApplicationTests {

	@Autowired
	NamesServiceImpl namesService;

	@Test
	@Order(1)
	void testProductoCategoria() {
		StepVerifier.create(namesService.names())
			.verifyComplete();

	}

}
