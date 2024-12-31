package com.microservicio.rastreador.reactivo.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor

@Data
public class Elemento {

  private String nombre;
  private String categoria;
  private double precioUnitario;
  private String tienda;

  public String getNombre() {
    return nombre;
  }

  public void setNombre(String nombre) {
    this.nombre = nombre;
  }

  public String getCategoria() {
    return categoria;
  }

  public void setCategoria(String categoria) {
    this.categoria = categoria;
  }

  public double getPrecioUnitario() {
    return precioUnitario;
  }

  public void setPrecioUnitario(double precioUnitario) {
    this.precioUnitario = precioUnitario;
  }

  public String getTienda() {
    return tienda;
  }

  public void setTienda(String tienda) {
    this.tienda = tienda;
  }
}
