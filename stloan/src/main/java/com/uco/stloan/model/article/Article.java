package com.uco.stloan.model.article;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Table(name="ARTICLES")
public class Article {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "ref")
    private String ref;
    @Column(name = "nombre")
    private String name;
    @Column(name = "cantidad")
    private int quantity;

    // estados : A: ACTIVO , I: INACTIVO
    @Column(name = "estado")
    private  String condition;

    public Article() {

    }

    public Article(String ref, String name, int condition ) {
        this.ref = ref;
        this.name = name;
        this.quantity = condition;
    }
}
