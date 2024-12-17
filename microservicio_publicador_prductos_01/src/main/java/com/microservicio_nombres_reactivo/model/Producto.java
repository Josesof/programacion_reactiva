package com.microservicio_nombres_reactivo.model;

import lombok.*;

@NoArgsConstructor
public class Producto {

  private Integer codProduto;
  private String nombre;
  private String categoria;
  private double precioUnitario;
  private int stock;

  public Producto(Integer codProduto, String nombre, String categoria, double precioUnitario, int stock) {
    this.codProduto = codProduto;
    this.nombre = nombre;
    this.categoria = categoria;
    this.precioUnitario = precioUnitario;
    this.stock = stock;
  }

  public Integer getCodProduto() {
    return codProduto;
  }

  public String getNombre() {
    return nombre;
  }

  public String getCategoria() {
    return categoria;
  }

  public double getPrecioUnitario() {
    return precioUnitario;
  }

  public int getStock() {
    return stock;
  }

  public void setCodProduto(Integer codProduto) {
    this.codProduto = codProduto;
  }

  public void setNombre(String nombre) {
    this.nombre = nombre;
  }

  public void setCategoria(String categoria) {
    this.categoria = categoria;
  }

  public void setPrecioUnitario(double precioUnitario) {
    this.precioUnitario = precioUnitario;
  }

  public void setStock(int stock) {
    this.stock = stock;
  }
}
