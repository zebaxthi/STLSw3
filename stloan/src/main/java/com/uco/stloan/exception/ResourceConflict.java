package com.uco.stloan.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class ResourceConflict extends RuntimeException{
    public ResourceConflict ( String message) {
        super(message);
    }
}
