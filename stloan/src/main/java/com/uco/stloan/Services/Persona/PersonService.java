package com.uco.stloan.Services.Persona;

import com.uco.stloan.exception.NotFoundEx;
import com.uco.stloan.model.person.Person;

import java.util.List;

public interface PersonService {

    public List<Person> findAll();
    public Person findById(String identificacion);
    public Person save(Person person);
    public void deleteById(String identificacion);
    public boolean partialUpdate(int id, String key, String value) throws NotFoundEx;


}
