package com.uco.stloan.Services.jwt;

import com.azure.core.exception.ResourceExistsException;
import com.uco.stloan.Repository.PersonRepository;
import com.uco.stloan.exception.ResourceConflict;
import com.uco.stloan.exception.ResourceNotFound;
import com.uco.stloan.model.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;


@Service
public class JwtUserDetailsService implements UserDetailsService {

    @Autowired
    private PersonRepository personRepository;

   @Autowired
    private PasswordEncoder bcryptEncoder;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Person person = personRepository.findByEmail(email);
        if (person == null) {
            throw new UsernameNotFoundException("User not found with email: " + email);
        }
        return new org.springframework.security.core.userdetails.User(person.getId().toString(), person.getPassword(),
                new ArrayList<>());
    }

    public UserDetails loadUserById(String id) throws UsernameNotFoundException {
        Person person = personRepository.findById(Long.parseLong(id)).get();
        if (person == null) {
            throw new UsernameNotFoundException("User not found with id: " + id);
        }
        return new org.springframework.security.core.userdetails.User(person.getId().toString(), person.getPassword(),
                new ArrayList<>());
    }

    public Person save(Person person) {
        Person personDB = personRepository.findByEmail(person.getEmail());
        if(personDB != null){
            throw new ResourceConflict("El usuario ya existe con ese email: " + person.getEmail());
        }
        person.setPassword(bcryptEncoder.encode(person.getPassword()));
        return personRepository.save(person);
    }

}