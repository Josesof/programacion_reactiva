package com.rastreador.app.service;

import com.rastreador.app.model.Elemento;
import reactor.core.publisher.Flux;

public interface ElmentoService {
  Flux<Elemento> elementoPorPrecio(double precioMax);
}
