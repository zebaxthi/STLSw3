package com.uco.stloan.exception;


import com.uco.stloan.web.ErrorResponse;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExeptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(ResourceNotFound.class)
    public ResponseEntity<ErrorResponse> handlerStudentNotFound( ResourceNotFound ex) {
        return ErrorResponse.createErrorResponse(HttpStatus.NOT_FOUND,
                "Resource Not Found",
                ex.getMessage());
    }

    @ExceptionHandler(ResourceBadRequest.class)
    public ResponseEntity<ErrorResponse> handlerStudentBadRequest( ResourceBadRequest ex){
        Map<String, String> details = new HashMap<>();
        ex.getBindingResult().getFieldErrors()
                .forEach(e -> {
                    details.put(e.getField(),"El campo '" + e.getField() + "' " + e.getDefaultMessage());

                });

        return ErrorResponse.createErrorResponse(HttpStatus.BAD_REQUEST,
                "Resource bad request",
                details);
    }

    @ExceptionHandler(ResourceConflict.class)
    public ResponseEntity<ErrorResponse> handlerStudentConflict( ResourceNotFound ex) {
        return ErrorResponse.createErrorResponse(HttpStatus.NOT_FOUND,
                "Resource conflict",
                ex.getMessage());
    }

    @ExceptionHandler(Unauthorized.class)
    public ResponseEntity<ErrorResponse> handlerStudentConflict(Unauthorized ex) {
        return ErrorResponse.createErrorResponse(HttpStatus.UNAUTHORIZED,
                "unauthorized user",
                ex.getMessage());
    }



    @ExceptionHandler(DataAccessException.class)
    public ResponseEntity<ErrorResponse> handlerDataAccessException(DataAccessException ex) {
        return  ErrorResponse.createErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR,"Data exeption",ex.getMessage());
    }

    @ExceptionHandler(DisabledException.class)
    public ResponseEntity<ErrorResponse> handlerDataAccessException(DisabledException ex) {
        return  ErrorResponse.createErrorResponse(HttpStatus.NOT_FOUND,"USER_DISABLED",ex.getMessage());
    }


    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<ErrorResponse> handlerDataAccessException(BadCredentialsException ex) {
        return  ErrorResponse.createErrorResponse(HttpStatus.BAD_REQUEST,"INVALID_CREDENTIALS",ex.getMessage());
    }

    @Override
    protected ResponseEntity<Object> handleNoHandlerFoundException ( NoHandlerFoundException ex, HttpHeaders headers, HttpStatus status, WebRequest request ) {

        ErrorResponse err = new ErrorResponse(LocalDateTime.now(), HttpStatus.NOT_FOUND,
                "Endpoint not found", ex.getMessage());
        return ResponseEntityBuilder.build(err);
    }

    @Override
    protected ResponseEntity<Object> handleHttpRequestMethodNotSupported ( HttpRequestMethodNotSupportedException ex, HttpHeaders headers, HttpStatus status, WebRequest request  ) {

        ErrorResponse err = new ErrorResponse(LocalDateTime.now(), HttpStatus.METHOD_NOT_ALLOWED,
                "Method not supported", ex.getMessage());
        return ResponseEntityBuilder.build(err);
    }






}
