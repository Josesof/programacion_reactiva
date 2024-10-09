package com.microservicio.envios.consumidor.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Document(collection ="pedido")
public class Envio {

  private int idEnvio;
  @JsonAlias(value="nombre")
  private String producto;
  private LocalDateTime fechaEnvio;
  private String direccion;
  private String estado;

}
