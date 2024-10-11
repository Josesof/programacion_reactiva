package com.microservicio.eureka01.controller;


import com.microservicio.eureka01.model.Producto;
import com.microservicio.eureka01.service.ProductosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
public class ProductosController {

  @Autowired
  private ProductosService productosService;

  @GetMapping(value = "productos")
  public ResponseEntity<Flux<Producto>> productos(){
    return new ResponseEntity<>(productosService.catalogo(), HttpStatus.OK);
  }

  @GetMapping(value = "productos/{categoria}")
  public ResponseEntity<Flux<Producto>> productosCategoria(@PathVariable("categoria") String categoria){
    return new ResponseEntity<>(productosService.productosCategoria(categoria), HttpStatus.OK);
  }

  @GetMapping(value= "producto")
  public ResponseEntity<Mono<Producto>> productoCodigo(@RequestParam("cod") Integer cod){
    return new ResponseEntity<>(productosService.productoCodigo(cod), HttpStatus.OK);
  }

  @PostMapping(value= "alta", consumes = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<Mono<Void>> altaProdcto(@RequestBody Producto producto){
    return new ResponseEntity<>(productosService.altaProducto(producto), HttpStatus.OK);
  }

  @DeleteMapping(value= "eliminar")
  public Mono<ResponseEntity<Producto>> eliminarProducto(@RequestParam("cod") Integer cod){
    return productosService.eliminarProducto(cod)
      .map(p -> new ResponseEntity<>(p,HttpStatus.OK))
      .switchIfEmpty(Mono.just(new ResponseEntity<>(HttpStatus.NOT_FOUND)));
  }

  @PutMapping(value= "producto")
  public Mono<ResponseEntity<Producto>> actualizaProducto(@RequestParam("cod") Integer cod,
                                          @RequestParam("precio") Double precio){
    return productosService.actualizarPrecio(cod, precio)
      .map(p -> new ResponseEntity<>(p,HttpStatus.OK))
      .switchIfEmpty(Mono.just(new ResponseEntity<>(HttpStatus.NOT_FOUND)));
  }


}
