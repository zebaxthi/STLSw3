package com.uco.stloan.Services.Loan;

import com.uco.stloan.Repository.LoanRepository;
import com.uco.stloan.Repository.PersonRepository;
import com.uco.stloan.exception.NotFoundEx;
import com.uco.stloan.exception.ResourceNotFound;
import com.uco.stloan.model.Loan;
import com.uco.stloan.model.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;
import java.util.*;
import java.util.function.Consumer;
import java.util.stream.Collectors;

@Service
public class LoanImpl implements LoanService{

    @Autowired
    private LoanRepository loanRepository;
    @Autowired
    private PersonRepository personRepository;


    @Override
    @Transactional(readOnly = true)
    public List<Loan> findAll() {
        return loanRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Loan findById(Long id) {
        return loanRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFound("Loan with id:" + id + " not found"));
    }



    @Override
    public List<Loan> finLoanStatus(int personUser,int articleID) {
        List<Loan> loans = loanRepository.findAll();
        List<Loan> loansFilter =  new ArrayList<>();
        List<String> activeArticles = new ArrayList<>();

        for (Loan loan : loans) {
            if (loan.getPersonUser()==personUser ) {  // Filtrar por ID de usuario
              loansFilter = loans.stream()
                        .filter(x -> x.getArticle() == articleID)
                        .collect(Collectors.toList());

            }
        }

        return loansFilter;
    }

    @Override
    public List<Loan> listItemsLentByPerson(int personUser)
    {
        List<Loan> loans = loanRepository.findAll();
         List<Loan> personList= new ArrayList<>();

       for (Loan loan :loans)
       {
           if (loan.getPersonUser() == personUser) {
               personList = loans.stream().filter(x -> x.getPersonUser() == personUser).collect(Collectors.toList());
           }

       }
       return personList;
    }


    @Override
    public Loan save(Loan loan) {
        return loanRepository.save(loan);
    }

    @Override
    public void deleteById(Long id) {
        loanRepository.findById(id);
    }

    @Override
    public boolean partialUpdate(long id, String key, String value) throws NotFoundEx {
        Optional<Loan> optional = loanRepository.findById(id);
        if (!optional.isPresent()) {
            throw new NotFoundEx("RESOURCE_NOT_FOUND");
        }
        Loan loan = optional.get();


        Map<String, Consumer<String>> setters = new HashMap<>();
        setters.put("personUser", val -> loan.setPersonUser(Integer.parseInt(val)));
        setters.put("personMonitor", val -> loan.setPersonMonitor(Integer.parseInt(val)));
        setters.put("article", val -> loan.setArticle(Integer.parseInt(val)));
        setters.put("qtyArticle", val -> loan.setQtyArticle(Integer.parseInt(val)));
        setters.put("dateStart", val -> loan.setDateStart(Date.valueOf(val)));
        setters.put("dateEnd", val -> loan.setDateEnd(Date.valueOf(val)));
        setters.put("isReturned", val -> loan.setReturned(Boolean.valueOf(val)));

        if (!setters.containsKey(key)) {
            throw new NotFoundEx("FIELD_NOT_FOUND");
        }
        setters.get(key).accept(value);

        loanRepository.save(loan);
        return true;
    }

    private Object parseValue(String key, String value) {
        if (key.equals("qtyArticle")) {
            return Integer.parseInt(value);
        }
        if (key.equals("isReturned")) {
            return Boolean.parseBoolean(value);
        }
        if (key.equals("dateStart") || key.equals("dateEnd")) {
            return value;
        }
        return value;
    }
}
