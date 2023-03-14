package com.uco.stloan.dto;

import lombok.Data;

import javax.validation.constraints.Size;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class ArticleDTO {


    private String ref;

    private String name;

    private int quantity;

    private  String status;

    public ArticleDTO(String ref, String name, int quantity, String status) {
        
    }
}
