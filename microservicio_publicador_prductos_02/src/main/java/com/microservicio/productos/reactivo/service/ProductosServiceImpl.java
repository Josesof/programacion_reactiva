package com.microservicio.productos.reactivo.service;

import com.microservicio.productos.reactivo.model.Producto;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

@Service
public class ProductosServiceImpl implements ProductosService{

  private static List<Producto> productos = new ArrayList<> (List.of(
    new Producto(101, "Pan", "Alimentacion", 1200, 30 ),
    new Producto(102, "Esponja", "Limpieza", 140, 43 ),
    new Producto(103, "Sofa", "Hogar", 1200, 10 ),
    new Producto(104, "Jarron", "Hogar", 39, 2 ),
    new Producto(105, "Arina", "Alimentacion", 200, 9),
    new Producto(106, "Fregona", "Limpieza", 6500, 11 ),
    new Producto(107, "Cubo", "Limpieza", 170, 12 )
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
      .filter(producto -> producto.getCodProduto()==cod)//Flux<Producto>
      .next();//Mono<Producto>
      //.switchIfEmpty(Mono.just(new Producto()))
  }

  @Override
  public Mono<Void> altaProducto(Producto producto) {
    return productoCodigo(producto.getCodProduto())
      .switchIfEmpty(Mono.just(producto).map(p ->{
        productos.add(producto);
        return p;
      })).then();//Mono<Void>
  }

  @Override
  public Mono<Producto> eliminarProducto(Integer cod) {
    return productoCodigo(cod)
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
