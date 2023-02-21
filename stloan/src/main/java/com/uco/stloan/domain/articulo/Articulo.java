package com.uco.stloan.domain.articulo;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Articulo {

    private Long id;
    private String ref;
    private String nombre;
    private int cantidad;

    public Articulo ( String ref, String nombre, int cantidad ) {
        this.ref = ref;
        this.nombre = nombre;
        this.cantidad = cantidad;
    }
}
