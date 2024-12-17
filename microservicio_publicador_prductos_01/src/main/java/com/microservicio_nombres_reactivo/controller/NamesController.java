package com.microservicio_nombres_reactivo.controller;

import com.microservicio_nombres_reactivo.model.Producto;
import com.microservicio_nombres_reactivo.service.NamesServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.util.List;

@CrossOrigin("*")
@RestController
public class NamesController {

  @Autowired
  private NamesServiceImpl namesService;

  @GetMapping(value="names")
  public ResponseEntity<Flux<String>> getNames() {
    return new ResponseEntity<>(namesService.names(), HttpStatus.OK);
  }

  @GetMapping(value = "names/{name}")
  public ResponseEntity<Mono<String>> getSearchNames(@PathVariable("name") String name) {
    return new ResponseEntity<>(namesService.coincidence(name), HttpStatus.OK);
  }
}
