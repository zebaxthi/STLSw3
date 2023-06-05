package com.uco.stloan.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
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
 @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
 private String dateStart;

 @Column(name="dateEnd")
 @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
 private String dateEnd;

 @Column(name="IsReturned")
 private Boolean isReturned;

 public Loan(Integer personUser, int personMonitor, int article, int qtyArticle, String dateStart, String dateEnd, Boolean isReturned) {
  this.personUser = personUser;
  this.personMonitor = personMonitor;
  this.article = article;
  this.qtyArticle = qtyArticle;
  this.dateStart = dateStart;
  this.dateEnd = dateEnd;
  this.isReturned = isReturned;
 }
 public Loan()
 {

 }

 public Integer getArticle() {
  return article;
 }

 public void setArticle(Integer article) {
  this.article = article;
 }

 public Integer getQtyArticle() {
  return qtyArticle;
 }

 public void setQtyArticle(Integer qtyArticle) {
  this.qtyArticle = qtyArticle;
 }

 public Integer getPersonUser() {
  return personUser;
 }

 public void setPersonUser(Integer personUser) {
  this.personUser = personUser;
 }

 public Integer getPersonMonitor() {
  return personMonitor;
 }

 public void setPersonMonitor(Integer personMonitor) {
  this.personMonitor = personMonitor;
 }

 public String getDateStart() {
  return dateStart;
 }

 public void setDateStart(String dateStart) {
  this.dateStart = dateStart;
 }

 public String getDateEnd() {
  return dateEnd;
 }

 public void setDateEnd(String dateEnd) {
  this.dateEnd = dateEnd;
 }

 public Boolean getReturned() {
  return isReturned;
 }

 public void setReturned(Boolean returned) {
  isReturned = returned;
 }

}
