package com.microservicio_nombres_reactivo.service;

import com.microservicio_nombres_reactivo.model.Producto;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

@Service
public class ProductosServiceImpl implements ProductosService{

  private static List<Producto> productos = new ArrayList<> (List.of(
    new Producto(101, "Leche", "Alimentacion", 0.89, 30 ),
    new Producto(102, "Jabon", "Limpieza", 0.23, 43 ),
    new Producto(103, "Mesa", "Hogar", 1200, 10 ),
    new Producto(104, "Television", "Hogar", 39, 22 ),
    new Producto(105, "Huevos", "Alimentacion", 200, 13),
    new Producto(105, "Azucar", "Alimentacion", 200, 13),
    new Producto(106, "Fregona", "Limpieza", 500, 30 ),
    new Producto(107, "Detergente", "Limpieza", 8.89, 30 )
  ));

  @Override
  public Flux<Producto> catalogo() {
    return Flux.fromIterable(productos)
      .delayElements(Duration.ofSeconds(2));
  }

  @Override
  public Flux<Producto> productosCategoria(String categoria) {
    return catalogo().filter(p -> p.getCategoria().equals(categoria));
  }

  @Override
  public Mono<Producto> productoCodigo(Integer cod) {
    return catalogo()
      .filter(producto -> producto.getCodProduto() == cod)//Flux<Producto>
      .next();//Mono<Producto>
      //.switchIfEmpty(Mono.just(new Producto()))
  }

  @Override
  public Mono<Void> altaProducto(Producto producto) {
    return productoCodigo(producto.getCodProduto())//Mono<Producto>
      .switchIfEmpty(Mono.just(producto).map(p ->{
        productos.add(producto);
        return p;
      }))//Mono<Producto>
      .then();//Mono<Void>
  }

  @Override
  public Mono<Producto> eliminarProducto(Integer cod) {
    return productoCodigo(cod)//Mono<Producto>
      .map(producto -> {
        productos.removeIf(productoEliminar -> productoEliminar.getCodProduto()==cod);
        return producto;
      });
  }

  @Override
  public Mono<Producto> actualizarPrecio(Integer cod, Double precio) {
    return productoCodigo(cod)
      .map(producto -> {
        producto.setPrecioUnitario(precio);
        return producto;
      });
  }
}
