package com.uco.stloan.controller;

import com.uco.stloan.dto.ArticleDTO;
import com.uco.stloan.dto.LoanDTO;
import com.uco.stloan.exception.ResourceBadRequest;
import com.uco.stloan.messagingLoan.MessageSenderBroker;
import com.uco.stloan.model.Article;
import com.uco.stloan.web.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1/rest/loan")
public class LoanController {

    @Autowired
    MessageSenderBroker messageSenderBroker;

    @PostMapping
    public ResponseEntity<Response> create(@Valid @RequestBody LoanDTO loan, BindingResult result) {
        if(result.hasErrors()){
            throw new ResourceBadRequest("Loan bad request",result);
        }
        messageSenderBroker.execute(loan, "sre");
        return Response.createResponse(HttpStatus.CREATED, HttpStatus.CREATED.toString());
    }
}
