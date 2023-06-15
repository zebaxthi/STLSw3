package com.uco.stloan.Services.Loan;

import com.uco.stloan.exception.NotFoundEx;
import com.uco.stloan.model.Loan;
import com.uco.stloan.model.Person;

import java.util.List;

public interface LoanService {
    public List<Loan> findAll();
    public Loan findById(Long id);

    public Loan findLoanByArticle(Long id);

    public List<Loan> finLoanStatus(int personUser,int articleID);
    public List<Loan>listItemsLentByPerson(int personUser);

    public Loan save(Loan loan);

    public void deleteById(Long id);

    public boolean partialUpdate( long id, String key, String value) throws NotFoundEx;



}
