package com.microservicio.reactivo.runners;

import com.microservicio.reactivo.model.Producto;
import org.springframework.boot.CommandLineRunner;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;


@Component
public class TestRunner implements CommandLineRunner {

  @Override
  public void run(String... args) throws Exception {

    WebClient webClient = WebClient.create("http://localhost:8000");

    /*
*
 Flux<Producto> fluxProd = webClient.get()
      .uri("/productos")
      .accept(MediaType.APPLICATION_JSON)
      .retrieve()
      .bodyToFlux(Producto.class);

    fluxProd.subscribe(p->System.out.println(p));
/*
*
    webClient
      .post()
      .uri("/alta")
      .body(Mono.just(
        new Producto(
          200,
          "prueba-service",
          "categoria-test",
          4.5,30)), Producto.class)
      .retrieve()
      .bodyToMono(Void.class)
      .doOnTerminate(
        ()->System.out.println("Se ha dado de alta al producto"))
      .block();
* */

    Mono<Producto> monoFind =
      webClient.get()
        .uri("/producto?cod=102")
        .accept(MediaType.APPLICATION_JSON)
        .retrieve()
        .bodyToMono(Producto.class);

    monoFind.subscribe(p->System.out.println(p));
    monoFind.switchIfEmpty(Mono.just(new Producto()).map(
      p -> {
        System.out.println("No se encontro ningun producto");
        return p;
      }
    )).block();

     webClient
      .delete()
      .uri("/eliminar?cod=102")
      .accept(MediaType.APPLICATION_JSON)
      .retrieve()
       .onStatus(h ->h.is4xxClientError(), t -> {
         System.out.println("No se encontro el producto");
         return Mono.empty();
       })
      .bodyToMono(Producto.class)
      .subscribe(p->System.out.println(p));




  }

}
