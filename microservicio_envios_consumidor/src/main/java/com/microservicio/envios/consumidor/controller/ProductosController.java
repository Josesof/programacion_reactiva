package com.microservicio.envios.consumidor.controller;

import com.microservicio.envios.consumidor.model.Envio;
import com.microservicio.envios.consumidor.service.ProductosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

@RestController
public class ProductosController {

  @Autowired
  private ProductosService productosService;


  public ResponseEntity<Flux<Envio>> enviosPendientes(@PathVariable String envio) {
    return new ResponseEntity<>(productosService.findByEstadoEnvio(envio), HttpStatus.OK);
  }


}
