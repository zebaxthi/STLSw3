package com.uco.stloan.Services.Persona;

import com.uco.stloan.dto.PersonDTO;
import com.uco.stloan.exception.NotFoundEx;
import com.uco.stloan.model.Person;

import java.util.List;

public interface PersonService {

    public List<Person> findAll();
    public Person findById(Long id);
    public Person save(@Valid PersonDTO person);
    public void deleteById(Long id);
    public boolean partialUpdate(Long id, String key, String value) throws NotFoundEx;

}
