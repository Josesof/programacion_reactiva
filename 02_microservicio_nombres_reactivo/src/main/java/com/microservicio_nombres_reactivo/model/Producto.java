package com.microservicio_nombres_reactivo.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Producto {

  private Integer codProduto;
  private String nombre;
  private String categoria;
  private double precioUnitario;
  private int stock;

}