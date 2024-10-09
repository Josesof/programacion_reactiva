package com.microservicio.pedidos.reactivo.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

import java.time.Duration;
import java.util.List;

@CrossOrigin("*")
@RestController
public class NamesController {

  @GetMapping(value="numeros")
  public Flux<String> getNames() {
    List<String>  names = List.of("one","two","three","four","five");
    return Flux.fromIterable(names).delayElements(Duration.ofSeconds(2));
  }
}
