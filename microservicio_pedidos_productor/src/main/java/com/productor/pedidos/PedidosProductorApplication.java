package com.productor.pedidos;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication(scanBasePackages = {"service","controller"})
public class PedidosProductorApplication {

	public static void main(String[] args) {
		SpringApplication.run(PedidosProductorApplication.class, args);
		System.out.println("Corriendo de manera correcta");
	}

}
