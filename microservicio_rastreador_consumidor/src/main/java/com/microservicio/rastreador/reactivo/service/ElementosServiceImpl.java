package com.microservicio.rastreador.reactivo.service;

import com.microservicio.rastreador.reactivo.model.Elemento;
import io.netty.handler.codec.base64.Base64Encoder;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;

import java.util.Base64;
@Slf4j
@Service
public class ElementosServiceImpl implements ElementosService {

  private static final Logger log = LoggerFactory.getLogger(ElementosServiceImpl.class);
  String url1 =  "http://localhost:8000";
  String url2 = "http://localhost:9000";
  @Value("$user")
  String user;
  @Value("$pwd")
  String pwd;
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
      .uri("/allProductos")
      .accept(MediaType.APPLICATION_JSON)
      .header("Authorization", "Basic "+getEncoderBase64Credentials("user", "user"))
      .retrieve()
      .bodyToFlux(Elemento.class)
      .map(e->{
        e.setTienda(tienda);
        return e;
      });
  }
  private String getEncoderBase64Credentials(String user, String pwd){
     String credential = user+":"+pwd;
    log.info("Iniciando la acción..."+credential);
     return Base64.getEncoder().encodeToString(credential.getBytes());
  }
}
