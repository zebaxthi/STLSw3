package com.uco.stloan.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Table(name = "LOANS")
public class Loan {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "idArticle")
    private Article article;

    @ManyToOne
    @JoinColumn(name ="idPerson")
    private Person person;

    // estados : A: ACTIVO , I: INACTIVO
    @Column(name = "status")
    private  String status;



    public Loan() {

    }

    public Loan(Long id, Article article, Person person, String status ) {
        this.id = id;
        this.article = article;
        this.person = person;
        this.status = status;
    }
}
