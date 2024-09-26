package com.microservicio.rastreador.reactivo.service;

import com.microservicio.rastreador.reactivo.model.Elemento;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;

import java.util.Base64;

@Service
public class ElementosServiceImpl implements ElementosService {

  @Value("$user")
  String user;
  @Value("$pwd")
  String pwd;

  String url1 = "http://localhost:8000";
  String url2 = "http://localhost:9000";
  @Override
  public Flux<Elemento> elementosPrecioMax(double precioMax) {
    Flux<Elemento> flux1 = catalogo(url1, "Amazon");
    Flux<Elemento> flux2 = catalogo(url2, "Walmart");
    return Flux.merge(flux1,flux2)
      .filter(e->e.getPrecioUnitario()<=precioMax);
  }

  private Flux<Elemento> catalogo(String url, String tienda) {
    WebClient webClient = WebClient.create(url);
    return webClient
      .get()
      .uri("/productos")
      .accept(MediaType.APPLICATION_JSON)
      .header("Authorization","Basic"+encoderCredentials(user,pwd))
      .retrieve()
      .bodyToFlux(Elemento.class)
      .map(e->{
        e.setTienda(tienda);
        return e;
      });
  }

  private String encoderCredentials(String user, String pwd){
    String credentials = user+":"+pwd;
    return Base64.getEncoder().encodeToString(credentials.getBytes());
  }
}
