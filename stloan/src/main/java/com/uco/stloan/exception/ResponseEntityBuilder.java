package com.uco.stloan.exception;

import com.uco.stloan.web.ErrorResponse;
import org.springframework.http.ResponseEntity;

public class ResponseEntityBuilder {
    public static ResponseEntity<Object> build ( ErrorResponse error ) {
        return new ResponseEntity<>(error, error.getStatus());
    }
}
