package com.uco.stloan.model.person;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Table(name="PEAPLE")
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "identificacion")
    private String identification;
    @Column(name = "nombre")
    private String name;
    @Column(name = "apellido")
    private String lastname;
    @Column(name = "email")
    private String email;
    @Column(name = "contraseña")
    private String password;
    @Column(name = "celular")
    private String cellular;
    @Column(name = "direccion")
    private String address;
    @Column(name = "rol")
    private String rol;
    @Column(name = "codigoRFID")
    private String codeRFID;
        // estados : A: ACTIVO , I: INACTIVO
    @Column(name = "estado")
    private  String condition;




    public Person( ) {
    }

    public Person(String identification, String name, String lastname, String email, String password, String cellular, String address, String rol, String codeRFID) {
        this.identification = identification;
        this.name = name;
        this.lastname = lastname;
        this.email = email;
        this.password = password;
        this.cellular = cellular;
        this.address = address;
        this.rol = rol;
        this.codeRFID = codeRFID;
    }
}
