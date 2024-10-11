package com.microservicio.eureka.service;


import com.microservicio.eureka.model.Elemento;
import reactor.core.publisher.Flux;

public interface ElementosService {
  Flux<Elemento> elementosPrecioMax(double precioMax);
}
