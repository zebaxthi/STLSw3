package com.uco.stloan.model.articulo;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;

@Entity
@Getter
@Setter
public class Articulo {

    private Long id;
    private String ref;
    private String nombre;
    private int cantidad;

    public Articulo() {

    }

    public Articulo (String ref, String nombre, int cantidad ) {
        this.ref = ref;
        this.nombre = nombre;
        this.cantidad = cantidad;
    }
}
