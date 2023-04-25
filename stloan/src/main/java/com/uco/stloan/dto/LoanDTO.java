package com.uco.stloan.dto;

import com.uco.stloan.model.Article;
import com.uco.stloan.model.Person;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class LoanDTO {

    @NotBlank
    @NotNull
    private Person personUser;
    @NotBlank
    @NotNull
    private Person personMonitor;

    @NotBlank
    @NotNull
    private Article article;

    @NotBlank
    @NotNull
    private int qtyArticle;

    @NotBlank
    @DateTimeFormat
    @NotNull
    private Date dateStart;

    @NotBlank
    @DateTimeFormat
    @NotNull
    private Date dateEnd;

    @NotBlank
    @NotNull
    private Boolean isReturned;

}
