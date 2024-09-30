package com.microservicio.productos.reactivo.service;

import com.microservicio.productos.reactivo.model.Producto;
import com.microservicio.productos.reactivo.repository.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;

@Service
public class ProductosServiceImpl implements ProductosService{

  @Autowired
  ProductoRepository productoRepository;

  @Override
  public Flux<Producto> catalogo() {
    return productoRepository.findAll()
      .delayElements(Duration.ofSeconds(2));
  }

  @Override
  public Flux<Producto> productosCategoria(String categoria) {
    return productoRepository.findByCategoria(categoria);
  }

  @Override
  public Mono<Producto> productoCodigo(Integer cod) {
    return productoRepository.findById(cod);
  }

  @Override
  public Mono<Void> altaProducto(Producto producto) {
    return productoCodigo(producto.getId())
      .switchIfEmpty(Mono.just(producto)
        .flatMap(p -> productoRepository.save(producto)))
        .then();//Mono<Void>
  }

  @Override
  public Mono<Producto> eliminarProducto(Integer cod) {
    return productoCodigo(cod)
      .flatMap(producto -> productoRepository.deleteById(cod)//Mono<Void>
        .then(Mono.just(producto))//Mono<Producto>
      );
  }

  @Override
  public Mono<Producto> actualizarPrecio(Integer cod, Double precio) {
    return productoCodigo(cod)
      .flatMap(producto -> {
        producto.setPrecioUnitario(precio);
        return productoRepository.save(producto);//Mono<Producto>
      });
  }
}
