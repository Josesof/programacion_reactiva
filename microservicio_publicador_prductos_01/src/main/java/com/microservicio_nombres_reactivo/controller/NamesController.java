package com.microservicio_nombres_reactivo.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

import java.time.Duration;
import java.util.List;

@CrossOrigin("*")
@RestController
public class NamesController {

  @GetMapping(value="names")
  public Flux<String> getNames() {
    List<String>  names = List.of("one","two","three","four","five");
    return Flux.fromIterable(names).delayElements(Duration.ofSeconds(2));
  }
}
