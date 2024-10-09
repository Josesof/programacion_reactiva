package com.microservicio.envios.consumidor.repository;

import com.microservicio.envios.consumidor.model.Envio;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Flux;

public interface ProductoRepository extends ReactiveMongoRepository<Envio, Integer> {


  Flux<Envio> findByEstado(String estado);


}

