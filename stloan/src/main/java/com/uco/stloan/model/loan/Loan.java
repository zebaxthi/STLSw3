package com.uco.stloan.model.loan;

import com.uco.stloan.model.article.Article;
import com.uco.stloan.model.person.Person;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Table(name = "PRESTAMO")
public class Loan {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "idArticle")
    private Article article;

    @ManyToOne
    @JoinColumn(name ="idPersona")
    private Person person;

    // estados : A: ACTIVO , I: INACTIVO
    @Column(name = "estado")
    private  String condition;



    public Loan() {

    }

    public Loan(Long id, Article article, Person person, String condition) {
        this.id = id;
        this.article = article;
        this.person = person;
        this.condition = condition;
    }
}
