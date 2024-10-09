package com.microservicio.pedidos.reactivo.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

import java.lang.annotation.Documented;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Document(collection ="productos")
public class Producto {


  private Integer id;
  private String nombre;
  private String categoria;
  private double precioUnitario;
  private int stock;


}
