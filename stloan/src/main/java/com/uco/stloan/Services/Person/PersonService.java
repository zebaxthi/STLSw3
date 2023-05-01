package com.uco.stloan.Services.Person;


import com.uco.stloan.exception.NotFoundEx;
import com.uco.stloan.model.Person;

import java.util.List;

public interface PersonService {

    public List<Person> findAll();
    public Person findById(Long id);
    public Person findByEmail(String email);
    public Person save(Person person);
    public void deleteById(Long id);
    public boolean partialUpdate(Long id, String key, String value) throws NotFoundEx;

}
