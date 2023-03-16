package com.uco.stloan.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@Getter
@Setter
public class ArticleDTO {

    @NotNull
    @NotBlank
    private String ref;

    @NotNull
    @NotBlank
    private String name;

    @NotNull
    @NotBlank
    private int quantity;


    @NotNull
    @NotBlank
    private  String status;

}
