package com.uco.stloan.model.prestamo;

import com.uco.stloan.model.articulo.Articulo;
import com.uco.stloan.model.persona.Persona;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Table(name = "PRESTAMO")
public class Prestamo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "idArticulo")
    private Articulo articulo;

    @ManyToOne
    @JoinColumn(name ="idPersona")
    private Persona persona;

    // estados : A: ACTIVO , I: INACTIVO
    @Column(name = "estado")
    private  String estado;



    public Prestamo() {

    }

    public Prestamo(Long id, Articulo articulo, Persona persona, String estado) {
        this.id = id;
        this.articulo = articulo;
        this.persona = persona;
        this.estado = estado;
    }
}
