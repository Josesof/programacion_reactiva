package com.microservicio.productos.reactivo.controller;

import com.microservicio.productos.reactivo.model.Producto;
import com.microservicio.productos.reactivo.service.ProductosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.reactive.CorsWebFilter;
import org.springframework.web.cors.reactive.UrlBasedCorsConfigurationSource;
import org.springframework.web.reactive.function.server.RequestPredicates;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
public class ProductosController {

  @Autowired
  private ProductosService productosService;

  @Bean
  public RouterFunction<ServerResponse> respuestas(){
    return RouterFunctions.route(RequestPredicates.GET("productos"),
      req->ServerResponse.ok() //BodyBuilder
        .body(productosService.catalogo(), Producto.class)//Mono<ServerResponse>
    )//RouterFuntion<ServerResponse>
      .andRoute(RequestPredicates.GET("productos/{categoria}"),
        req->ServerResponse.ok() //BodyBuilder
          .body(productosService.productosCategoria(req.pathVariable("categoria")), Producto.class)//Mono<ServerResponse>
      )
      .andRoute(RequestPredicates.GET("productos/{categoria}"),
        req->ServerResponse.ok() //BodyBuilder
          .body(productosService.productoCodigo(req.queryParam("cod").map(s->Integer.parseInt(s)).get()), Producto.class)//Mono<ServerResponse>
      )
      .andRoute(RequestPredicates.POST("alta"),
        req->req.bodyToMono(Producto.class)//Mono<Producto>
          .flatMap(p-> {return productosService.altaProducto(p);})
          .flatMap(v->ServerResponse.ok()//BodyBuilder
            .build()//Mono<ServerResponse>
          )//Mono<ServerResponse>
      )//RouterFuntion<ServerResponse>
      .andRoute(RequestPredicates.DELETE("eliminar"),
        req->productosService.eliminarProducto(req.queryParam("cod").map(s->Integer.parseInt(s)).get()) //Mono<Producto>
          .flatMap(p -> ServerResponse.ok()//BodyBuilder
            .bodyValue(p)//Mono<ServerResponse>
          )//Mono<ServerResponse>
          .switchIfEmpty(ServerResponse.status(HttpStatus.NOT_FOUND)//BodyBuilder
            .build()//Mono<ServerResponse>
          )
        )
      .andRoute(RequestPredicates.PUT("actualizar"),
        req -> productosService.actualizarPrecio(req.queryParam("cod")
          .map(c->Integer.parseInt(c)).get(),
          req.queryParam("precio").map(p->Double.parseDouble(p)).get())
          .flatMap(p->ServerResponse.ok()
            .bodyValue(p)
          )//Mono<ServerResponse>
          .switchIfEmpty(ServerResponse.status(HttpStatus.NOT_FOUND)//BodyBuilder
            .build()//Mono<ServerResponse>
          )
        );
  }
  @Bean
  CorsWebFilter corsFilter() {
    CorsConfiguration config = new CorsConfiguration();
    config.setAllowCredentials(true);
    config.addAllowedOrigin("*");
    config.addAllowedHeader("*");
    config.addAllowedMethod("*");
    UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
    source.registerCorsConfiguration("/**", config);
    return new CorsWebFilter(source);
  }

}
