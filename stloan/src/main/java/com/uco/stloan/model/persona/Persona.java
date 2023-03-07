package com.uco.stloan.model.persona;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
public class Persona {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "identificacion")
    private String identificacion;
    @Column(name = "nombre")
    private String nombre;
    @Column(name = "apellido")
    private String apellido;
    @Column(name = "email")
    private String email;
    @Column(name = "contraseña")
    private String contraseña;
    @Column(name = "celular")
    private String celular;
    @Column(name = "direccion")
    private String direccion;
    @Column(name = "rol")
    private String rol;
    @Column(name = "codigoRFID")
    private String codigoRFID;
        // estados : A: ACTIVO , I: INACTIVO
    @Column(name = "estado")
    private  String estado;




    public Persona ( ) {
    }

    public Persona ( String identificacion, String nombre, String apellido, String email, String contraseña, String celular, String direccion, String rol, String codigoRFID ) {
        this.identificacion = identificacion;
        this.nombre = nombre;
        this.apellido = apellido;
        this.email = email;
        this.contraseña = contraseña;
        this.celular = celular;
        this.direccion = direccion;
        this.rol = rol;
        this.codigoRFID = codigoRFID;
    }
}
