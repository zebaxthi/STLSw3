package com.uco.stloan.domain.persona;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.autoconfigure.domain.EntityScan;

import javax.persistence.Entity;

@Entity
@Getter
@Setter
public class Persona {

    private Long id;
    private String identificacion;
    private String nombre;
    private String apellido;
    private String email;
    private String contraseña;
    private String celular;
    private String direccion;
    private String rol;
    private String codigoRFID;

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
