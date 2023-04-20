package com.uco.stloan.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Email;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name="PERSONS")
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "identification")
    private String identification;
    @Column(name = "name")
    private String name;
    @Column(name = "lastname")
    private String lastname;
    @Column(name = "email")
    @Email
    private String email;
    @Column(name = "password")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;
    @Column(name = "mobile")
    private String mobile;
    @Column(name = "address")
    private String address;
    @Column(name = "rol")
    private Rol rol;
    @Column(name = "RFID")
    private String RFID;

    @OneToMany(mappedBy = "person")
    private List<Loan> loanList;





    public Person( ) {
    }

    public Person( String identification, String name, String lastname, String email, String password, String mobile, String address, Rol rol, String RFID ) {
        this.identification = identification;
        this.name = name;
        this.lastname = lastname;
        this.email = email;
        this.password = password;
        this.mobile = mobile;
        this.address = address;
        this.rol = rol;
        this.RFID = RFID;
    }
}
