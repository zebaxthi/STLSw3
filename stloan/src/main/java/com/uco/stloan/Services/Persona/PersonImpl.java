package com.uco.stloan.Services.Persona;

import com.uco.stloan.Repository.Person.PersonRepository;
import com.uco.stloan.exception.NotFoundEx;
import com.uco.stloan.model.person.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class PersonImpl implements PersonService {

    private final PersonRepository personRepository;
    @Autowired
    public PersonImpl(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Person> findAll ( ) {
        return personRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Person findById (String identification ) {
        return null; //personRepository.findByIdentification(identification);
    }

    @Override
    @Transactional
    public Person save (Person person) {
        return personRepository.save(person);
    }

    @Override
    @Transactional
    public void deleteById ( String identification ) {
        //personaRepository.deleteByIdentification(identification);
    }

    @Override
    public boolean partialUpdate(int id, String key, String value) throws NotFoundEx {
        Optional<Person> optional = personRepository.findById((long) id);
        if (optional.isPresent()) {
            Person person = optional.get();

            if (key.equalsIgnoreCase("identification")) {
                person.setIdentification(value);
            }
            if (key.equalsIgnoreCase("name")) {
                person.setName(value);
            }
            if (key.equalsIgnoreCase("lastname")) {
                person.setLastname(value);
            }
            if (key.equalsIgnoreCase("email")) {
                person.setEmail(value);
            }
            if (key.equalsIgnoreCase("password")) {
                person.setPassword(value);
            }
            if (key.equalsIgnoreCase("cellular")) {
                person.setCellular(value);
            }
            if (key.equalsIgnoreCase("address")) {
                person.setAddress(value);
            }
            if (key.equalsIgnoreCase("rol")) {
                person.setRol(value);
            }
            if (key.equalsIgnoreCase("codeRFID")) {
                person.setCodeRFID(value);
            }

            personRepository.save(person);
            return true;
        } else {
            throw new NotFoundEx("RESOURCE_NOT_FOUND");
        }
    }
}
