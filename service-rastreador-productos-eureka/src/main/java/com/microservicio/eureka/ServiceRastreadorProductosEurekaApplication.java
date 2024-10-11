package com.microservicio.eureka;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.web.reactive.function.client.WebClient;

@SpringBootApplication
public class ServiceRastreadorProductosEurekaApplication {

	public static void main(String[] args) {
		SpringApplication.run(ServiceRastreadorProductosEurekaApplication.class, args);
		System.out.println("## Aplicacion Rastreador Eureka Corriendo ##");
	}

	@Bean
	@LoadBalanced
	public WebClient.Builder client()  {
		return WebClient.builder();
	}

}
