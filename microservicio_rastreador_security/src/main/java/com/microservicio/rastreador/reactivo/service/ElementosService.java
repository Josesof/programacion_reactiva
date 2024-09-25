package com.microservicio.rastreador.reactivo.service;

import com.microservicio.rastreador.reactivo.model.Elemento;
import reactor.core.publisher.Flux;

public interface ElementosService {
  Flux<Elemento> elementosPrecioMax(double precioMax);
}
