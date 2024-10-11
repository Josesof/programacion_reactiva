package com.microservicio.eureka01.service;


import com.microservicio.eureka01.model.Producto;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ProductosService {
  Flux<Producto> catalogo();
  Flux<Producto> productosCategoria(String categoria);
  Mono<Producto> productoCodigo(Integer cod);
  Mono<Void> altaProducto(Producto producto);
  Mono<Producto> eliminarProducto(Integer cod);
  Mono<Producto> actualizarPrecio(Integer cod, Double precio);
}
