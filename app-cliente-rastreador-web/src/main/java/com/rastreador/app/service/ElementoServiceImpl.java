package com.rastreador.app.service;

import com.rastreador.app.model.Elemento;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;

@Service
public class ElementoServiceImpl implements ElmentoService{

  String url="http://localhost:8080";

  @Override
  public Flux<Elemento> elementoPorPrecio(double precioMax) {
    WebClient webClient = WebClient.create(url);
    return webClient.get()
      .uri("/elementos/"+precioMax)
      .accept(MediaType.APPLICATION_JSON)
      .retrieve()
      .bodyToFlux(Elemento.class);
  }
}
