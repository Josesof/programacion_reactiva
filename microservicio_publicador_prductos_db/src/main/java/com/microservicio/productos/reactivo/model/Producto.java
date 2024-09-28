package com.microservicio.productos.reactivo.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Persistent;
import org.springframework.data.annotation.Transient;
import org.springframework.data.domain.Persistable;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Table(name="productos")
public class Producto implements Persistable<Integer> {

  @Id
  @Column(value="codProducto")
  private Integer codProducto;
  private String nombre;
  private String categoria;
  @Column(value="precioUnitario")
  private double precioUnitario;
  private int stock;
  @Transient //para que la base de datos no lo persista
  private boolean nuevo;

  @Override
  public Integer getId() {
    return codProducto;
  }

  @Override
  public boolean isNew() {
    return nuevo;
  }
}
