package com.microservicio.productos.reactivo.repository;

import com.microservicio.productos.reactivo.model.Producto;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ProductoRepository extends ReactiveCrudRepository<Producto, Integer> {
  Flux<Producto> findByCategoria(String categoria);

  @Transactional
  Mono<Void> deleteByNombre(String nombre);

  @Transactional
  @Query(value = "delete from productos where precio>?")
  Mono<Void> deleteByPrecio(double precioMax);
}

