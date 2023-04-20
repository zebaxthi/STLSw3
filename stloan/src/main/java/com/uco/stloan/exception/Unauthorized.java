package com.uco.stloan.exception;

import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.UNAUTHORIZED)
public class Unauthorized extends  RuntimeException{


    private BindingResult bindingResult;

    public Unauthorized ( String message, BindingResult bindingResult){
        super(message);
        this.bindingResult = bindingResult;
    }

    public BindingResult getBindingResult ( ) {
        return bindingResult;
    }

    public void setBindingResult ( BindingResult bindingResult ) {
        this.bindingResult = bindingResult;
    }

}
