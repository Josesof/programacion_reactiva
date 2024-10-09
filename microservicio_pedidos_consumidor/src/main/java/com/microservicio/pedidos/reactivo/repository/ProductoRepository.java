package com.microservicio.pedidos.reactivo.repository;

import com.microservicio.pedidos.reactivo.model.Producto;
import org.springframework.data.mongodb.repository.DeleteQuery;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ProductoRepository extends ReactiveMongoRepository<Producto, Integer> {
  Flux<Producto> findByCategoria(String categoria);


  Mono<Void> deleteByNombre(String nombre);


  @DeleteQuery(value = "{'precioUnitario':{$lt:?0}}")
  Mono<Void> deleteByPrecio(double precioMax);
}

