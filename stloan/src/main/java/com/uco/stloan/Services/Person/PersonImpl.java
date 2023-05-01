package com.uco.stloan.Services.Person;

import com.uco.stloan.Repository.PersonRepository;

import com.uco.stloan.exception.NotFoundEx;

import com.uco.stloan.exception.ResourceNotFound;
import com.uco.stloan.model.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Consumer;

@Service
public class PersonImpl implements PersonService {

    @Autowired
    private PersonRepository personRepository;




    @Override
    @Transactional(readOnly = true)
    public List<Person> findAll ( ) {
        return personRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Person findById (Long id ) {
        return personRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFound("Person with id:" + id + " not found"));
    }

    @Override
    @Transactional(readOnly = true)
    public Person findByEmail(String email) {
        return personRepository.findByEmail(email);
    }
    
    @Override
    public Person save(Person person) {
        return personRepository.save(person);
    }

    @Override
    @Transactional
    public void deleteById (Long id) {
        personRepository.findById(id);
    }


    @Override
    public boolean partialUpdate(Long id, String key, String value) throws NotFoundEx {
        Optional<Person> optional = personRepository.findById(id);
        if (!optional.isPresent()) {
            throw new NotFoundEx("RESOURCE_NOT_FOUND");
        }
        Person person = optional.get();


        Map<String, Consumer<String>> setters = new HashMap<>();
        setters.put("identification", person::setIdentification);
        setters.put("name", person::setName);
        setters.put("lastname", person::setLastname);
        setters.put("email", person::setEmail);
        setters.put("password", person::setPassword);
        setters.put("cellular", person::setMobile);
        setters.put("address", person::setAddress);
        setters.put("rol", person::setRol);
        setters.put("codeRFID", person::setRFID);

        if (!setters.containsKey(key)) {
            throw new NotFoundEx("FIELD_NOT_FOUND");
        }
        setters.get(key).accept(value);

        personRepository.save(person);
        return true;
    }

   }
