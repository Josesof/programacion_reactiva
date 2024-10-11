package com.microservicio.eureka01;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ServiceProductosEureka01Application {

	public static void main(String[] args) {
		SpringApplication.run(ServiceProductosEureka01Application.class, args);
		System.out.println("Systema productos #01 - corriendo de manera correcta");
	}

}
