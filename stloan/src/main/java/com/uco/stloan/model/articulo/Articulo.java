package com.uco.stloan.model.articulo;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Table(name="ARTICLES")
public class Articulo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "ref")
    private String ref;
    @Column(name = "nombre")
    private String nombre;
    @Column(name = "cantidad")
    private int cantidad;

    // estados : A: ACTIVO , I: INACTIVO
    @Column(name = "estado")
    private  String estado;

    public Articulo() {

    }

    public Articulo (String ref, String nombre, int cantidad ) {
        this.ref = ref;
        this.nombre = nombre;
        this.cantidad = cantidad;
    }
}
