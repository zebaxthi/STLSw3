package com.uco.stloan.controller;

import com.uco.stloan.Services.Persona.PersonService;
import com.uco.stloan.dto.PatchDTO;
import com.uco.stloan.dto.PersonDTO;
import com.uco.stloan.exception.NotFoundEx;
import com.uco.stloan.exception.NotYetImplementedEx;
import com.uco.stloan.model.Person;
import com.uco.stloan.web.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.naming.Binding;
import javax.validation.Valid;
import java.util.List;

@RestController
    @RequestMapping("/api/v1/rest/persons")
    public class PersonController {
        @Autowired
        private PersonService personService;

        @GetMapping
        public ResponseEntity<Response> listPerson() {
           return Response.createResponse(HttpStatus.OK, personService.findAll());
        }

        @GetMapping("/{id}")
        public ResponseEntity<Response> personById(@PathVariable Long id) {
            return Response.createResponse(HttpStatus.OK, personService.findById(id));
        }

        @PostMapping
        public ResponseEntity<Response> create(@Valid @RequestBody PersonDTO person, BindingResult result) {


            Person newPerson = new Person(person.getIdentification(),person.getName(),person.getLastname(),
                    person.getEmail(),person.getPassword(),person.getMobile(),person.getAddress(),person.getRol(),
                    person.getRFID());
            personService.save(newPerson, result);
            return  Response.createResponse(HttpStatus.CREATED, personService.save(newPerson, result));

        }

        @DeleteMapping
        public void delete ( @RequestParam(required = true) Long id){

            personService.deleteById (id);
        }

        @PutMapping
        public ResponseEntity<Person> edit(@Valid @RequestBody Person person,
                                           BindingResult  result,
                                           @RequestParam(required = true) Long id ){

            Person personDB = null;
            Person personCurrent;

            personDB = personService.findById(id);
            if(personDB == null){
                return new ResponseEntity<>(personService.findById(id), HttpStatus.BAD_REQUEST);
            }
            personCurrent = new Person(person.getIdentification(),
                    person.getName(), person.getLastname(),
                    person.getEmail(), person.getPassword(),
                    person.getMobile(), person.getAddress(),
                    person.getRol(), person.getRFID());

            //empleadoDB.setNombre(empleadoCurrent.getNombre());
            personDB.setIdentification(personCurrent.getIdentification());
            personDB.setName(personCurrent.getName());
            personDB.setLastname(personCurrent.getLastname());
            personDB.setEmail(personCurrent.getEmail());
            personDB.setPassword(personCurrent.getPassword());
            personDB.setMobile(personCurrent.getMobile());
            personDB.setAddress(personCurrent.getAddress());
            personDB.setRol(personCurrent.getRol());
            personDB.setRFID(personCurrent.getRFID());




            personDB = personService.save(personDB, result);
            return new ResponseEntity<>(person, HttpStatus.CREATED);
        }

        @PatchMapping("/{id}")
        public ResponseEntity<Boolean> updatePartially(@PathVariable(name = "id") Long id,
                                                       @RequestBody PatchDTO dto) throws NotYetImplementedEx, NotFoundEx {
            // skipping validations for brevity
            if (dto.getOp().equalsIgnoreCase("update")) {
                boolean result = personService.partialUpdate(id, dto.getKey(), dto.getValue());
                return new ResponseEntity<>(result, HttpStatus.ACCEPTED);
            } else {
                throw new NotYetImplementedEx("NOT_YET_IMPLEMENTED");
            }
        }


    }


