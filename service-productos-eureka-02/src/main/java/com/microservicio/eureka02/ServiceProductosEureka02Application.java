package com.microservicio.eureka02;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ServiceProductosEureka02Application {

	public static void main(String[] args) {
		SpringApplication.run(ServiceProductosEureka02Application.class, args);
		System.out.println("Systema productos #02 - corriendo de manera correcta");
	}

}
