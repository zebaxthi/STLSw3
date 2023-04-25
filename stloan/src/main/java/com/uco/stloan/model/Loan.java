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

//  @ManyToOne
//  @JoinColumn(name = "personUser_id")
 @Column(name="personUser_id")
 private Integer personUser;
//  @ManyToOne
//  @JoinColumn(name = "personMonitor_id")
 @Column(name="personMonitor_id")
 private Integer personMonitor;

//  @ManyToOne
//  @JoinColumn(name = "article_id")
 @Column(name="article_id")
 private Integer article;

 @Column(name="qtyArticle")
 private Integer qtyArticle;

 @Column(name="dateStart")
 @DateTimeFormat
 private Date dateStart;

 @Column(name="dateEnd")
 @DateTimeFormat
 private Date dateEnd;

 @Column(name="IsReturned")
 private Boolean isReturned;



}
