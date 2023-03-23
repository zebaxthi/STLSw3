package com.uco.stloan.Services.jwt;

import com.uco.stloan.Repository.PersonRepository;
import com.uco.stloan.model.Person;
import org.springframework.beans.factory.annotation.Autowired;
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
        return new org.springframework.security.core.userdetails.User(person.getEmail(), person.getPassword(),
                new ArrayList<>());
    }

    public Person save(Person person) {
        person.setPassword(bcryptEncoder.encode(person.getPassword()));
        return personRepository.save(person);
    }

}