package com.microservicio.rastreador.reactivo.controller;

import com.microservicio.rastreador.reactivo.model.Elemento;
import com.microservicio.rastreador.reactivo.service.ElementosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

@RestController
public class ElemntosController {

  @Autowired
  ElementosService elementosService;

  @GetMapping(value = "/elementos/{precio}")
  public ResponseEntity<Flux<Elemento>> elementosPrecio(@PathVariable("precio") double precioMax){
    return new ResponseEntity<>(elementosService.elementosPrecioMax(precioMax), HttpStatus.OK);
  }
}
