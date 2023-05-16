package com.uco.stloan.controller;

import com.uco.stloan.Services.Loan.LoanService;
import com.uco.stloan.dto.LoanDTO;
import com.uco.stloan.dto.PatchDTO;
import com.uco.stloan.exception.NotFoundEx;
import com.uco.stloan.exception.NotYetImplementedEx;
import com.uco.stloan.exception.ResourceBadRequest;
import com.uco.stloan.model.Loan;
import com.uco.stloan.web.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1/rest/loan")
public class LoanController {

    @Autowired
    private LoanService loanService;

    @GetMapping
    public ResponseEntity<Response> listLoans() {
        return Response.createResponse(HttpStatus.OK, loanService.findAll());
    }

    @GetMapping("/listPersonArticle/{id}")
    public ResponseEntity<Response>listPersonArticle(@PathVariable int id)
    {
        return Response.createResponse(HttpStatus.OK,loanService.listItemsLentByPerson(id));
    }
    @GetMapping("/{id}")
    public ResponseEntity<Response> loanById(@PathVariable Long id) {
        return Response.createResponse(HttpStatus.OK, loanService.findById(id));
    }

    @GetMapping("/{personUser}/{articleID}")
    public ResponseEntity<Response> LoansUser(@PathVariable int personUser,@PathVariable int articleID) {
        return Response.createResponse(HttpStatus.OK,loanService.finLoanStatus(personUser,articleID));
    }


    @PostMapping
    public ResponseEntity<Response> create(@Valid @RequestBody LoanDTO loan, BindingResult result) {
        if (result.hasErrors()) {
            throw new ResourceBadRequest("Article bad request", result);
        }

        Loan newLoan = new Loan(loan.getPersonUser(), loan.getPersonMonitor(), loan.getArticle(),
                loan.getQtyArticle(), loan.getDateStart(), loan.getDateEnd(), loan.getIsReturned());
        return Response.createResponse(HttpStatus.CREATED, loanService.save(newLoan));
    }

    @DeleteMapping
    public void delete(@RequestParam(required = true) Long id) {
        loanService.deleteById(id);
    }

    @PutMapping
    public ResponseEntity<Response> edit(@Valid @RequestBody LoanDTO loanDTO,
                                         BindingResult result,
                                         @RequestParam(required = true) Long id) {

        Loan loanDB = null;
        Loan loanCurrent;

        loanDB = loanService.findById(id);
        if (loanDB == null) {
            throw new ResourceBadRequest("Article bad request", result);
        }

        loanCurrent = new Loan(loanDTO.getPersonUser(), loanDTO.getPersonMonitor(), loanDTO.getArticle(),
                loanDTO.getQtyArticle(), loanDTO.getDateStart(), loanDTO.getDateEnd(), loanDTO.getIsReturned());

        loanDB.setPersonUser(loanCurrent.getPersonUser());
        loanDB.setPersonMonitor(loanCurrent.getPersonMonitor());
        loanDB.setArticle(loanCurrent.getArticle());
        loanDB.setQtyArticle(loanCurrent.getQtyArticle());
        loanDB.setDateStart(loanCurrent.getDateStart());
        loanDB.setDateEnd(loanCurrent.getDateEnd());
        loanDB.setReturned(loanCurrent.getReturned());


        return Response.createResponse(HttpStatus.CREATED, loanService.save(loanDB));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Boolean> updatePartially(@PathVariable(name = "id") int id,
                                                   @RequestBody PatchDTO dto) throws NotYetImplementedEx, NotFoundEx {
        // skipping validations for brevity
        if (dto.getOp().equalsIgnoreCase("update")) {
            boolean result = loanService.partialUpdate(id, dto.getKey(), dto.getValue());
            return new ResponseEntity<>(result, HttpStatus.ACCEPTED);
        } else {
            throw new NotYetImplementedEx("NOT_YET_IMPLEMENTED");
        }

    }
}
