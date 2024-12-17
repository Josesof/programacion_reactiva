package com.microservicio_nombres_reactivo.service;


import com.microservicio_nombres_reactivo.model.Producto;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

@Service
public class NamesServiceImpl {


  private static  List<String> nameString = new ArrayList<>(List.of(
    "Alejandro", "Beatriz", "Carlos", "Diana", "Eduardo",
    "Fernanda", "Gabriel", "Hilda", "Iván", "Julieta",
    "Karen", "Luis", "María", "Nicolás", "Olivia",
    "Pablo", "Quintina", "Raúl", "Sofía", "Tomás",
    "Ulises", "Valeria", "Walter", "Ximena", "Yolanda",
    "Zacarías", "Andrea", "Bruno", "Camila", "Daniel",
    "Elena", "Francisco", "Graciela", "Héctor", "Inés",
    "Jorge", "Karla", "Leonardo", "Mónica", "Natalia",
    "Oscar", "Patricia", "Roberto", "Susana", "Tobías",
    "Verónica", "Wilfredo", "Xavier", "Yvette", "Zoé"
  ));

  public Flux<String> names() {
    return Flux.fromIterable(nameString).delayElements(Duration.ofSeconds(2));
  }

  public Mono<String> coincidence(String name) {
    return Mono.just(nameString.stream().anyMatch(r -> r.equals(name)) ? name : "name doesn't exist");
  }



}
