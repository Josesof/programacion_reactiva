package com.microservicio.pedidos.reactivo;

import com.microservicio.pedidos.reactivo.model.Producto;
import com.microservicio.pedidos.reactivo.service.ProductosService;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import reactor.test.StepVerifier;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@SpringBootTest
public class ServicesTest {

  @Autowired
  ProductosService productosService;

  @Test
  @Order(1)
  void testProductoCategoria() {
    StepVerifier.create(productosService.productosCategoria("Alimentacion"))
      .expectNextMatches(p->p.getNombre().equals("Leche"))
      .expectNextMatches(p->p.getNombre().equals("Huevos"))
      .expectNextMatches(p->p.getNombre().equals("Azucar"))
      .verifyComplete();

  }

  @Test
  @Order(2)
  void testEliminarProducto() {
    StepVerifier.create(productosService.eliminarProducto(103))
      .expectNextMatches(producto -> producto.getNombre().equals("Mesa"))
      .verifyComplete();
  }

  @Test
  @Order(3)
  void testAltaProducto() {
    Producto producto = new Producto(250,"ejemplo","nueva",10,3,true);
    StepVerifier.create(productosService.altaProducto(producto))
      .expectComplete()
      .verify();
  }

  @Test
  @Order(4)
  void testCatalog() {
    StepVerifier.create(productosService.catalogo())
      .expectNextCount(8)
      .verifyComplete();
  }
}
