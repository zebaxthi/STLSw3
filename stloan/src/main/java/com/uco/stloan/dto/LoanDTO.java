package com.uco.stloan.dto;

import com.uco.stloan.model.Article;
import com.uco.stloan.model.Loan;
import com.uco.stloan.model.Person;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;
import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class LoanDTO {

    @NotNull
    @PositiveOrZero
    private int personUser;
    //@NotBlank
    @NotNull
    @PositiveOrZero
    private int personMonitor;

    //@NotBlank
    @NotNull
    @PositiveOrZero
    private int article;

    //@NotBlank
    @NotNull
    @PositiveOrZero
    private int qtyArticle;

    //@NotBlank
    @DateTimeFormat
    @NotNull
    private Date dateStart;

    //@NotBlank
    @DateTimeFormat
    @NotNull
    private Date dateEnd;

    //@NotBlank
    @NotNull
    private Boolean isReturned;

    public Integer getPersonUser() {
        return personUser;
    }



    /*

    public void setPersonUser(Integer personUser) {
        this.personUser = personUser;
    }

    public Integer getPersonMonitor() {
        return personMonitor;
    }

    public void setPersonMonitor(Integer personMonitor) {
        this.personMonitor = personMonitor;
    }
*/
    public Integer getArticle() {
        return article;
    }/*

    public void setArticle(Integer article) {
        this.article = article;
    }

    public int getQtyArticle() {
        return qtyArticle;
    }

    public void setQtyArticle(int qtyArticle) {
        this.qtyArticle = qtyArticle;
    }

    public Date getDateStart() {
        return dateStart;
    }

    public void setDateStart(Date dateStart) {
        this.dateStart = dateStart;
    }

    public Date getDateEnd() {
        return dateEnd;
    }

    public void setDateEnd(Date dateEnd) {
        this.dateEnd = dateEnd;
    }

    public Boolean getReturned() {
        return isReturned;
    }

    public void setReturned(Boolean returned) {
        isReturned = returned;
    }*/
}
