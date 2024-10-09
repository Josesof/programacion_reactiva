package com.microservicio.envios.consumidor.service;

import com.microservicio.envios.consumidor.model.Envio;
import reactor.core.publisher.Flux;

public interface ProductosService {
  Flux<Envio> findByEstadoEnvio(String estado);
}
