package com.microservicio.productos.reactivo.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Table(name="productos")
public class Producto {
  @Id
  @Column("cod_produto")
  private Integer codProduto;
  private String nombre;
  private String categoria;
  private double precioUnitario;
  private int stock;

}
