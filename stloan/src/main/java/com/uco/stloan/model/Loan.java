package com.uco.stloan.model;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name="LOAN")
public class Loan {

 @Id
 @GeneratedValue(strategy = GenerationType.IDENTITY)
 private Long id;

 @ManyToOne
 @JoinColumn(name = "personUser_id")
 private Person personUser;
 @ManyToOne
 @JoinColumn(name = "personMonitor_id")
 private Person personMonitor;

 @ManyToOne
 @JoinColumn(name = "article_id")
 private Article article;

 @Column(name="qtyArticle")
 private int qtyArticle;

 @Column(name="dateStart")
 @DateTimeFormat
 private Date dateStart;

 @Column(name="dateEnd")
 @DateTimeFormat
 private Date dateEnd;

 @Column(name="IsReturned")
 private Boolean isReturned;



}
