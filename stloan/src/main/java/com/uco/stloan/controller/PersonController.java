package com.uco.stloan.controller;

import com.uco.stloan.Services.Person.PersonService;
import com.uco.stloan.dto.PatchDTO;
import com.uco.stloan.exception.NotFoundEx;
import com.uco.stloan.exception.NotYetImplementedEx;
import com.uco.stloan.exception.ResourceBadRequest;
import com.uco.stloan.model.Person;
import com.uco.stloan.web.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;


import javax.validation.Valid;




@RestController
@RequestMapping("/api/v1/rest/persons")
public class PersonController {
    @Autowired
    private PersonService personService;




    @GetMapping("/{id}")
    public ResponseEntity<Response> personById ( @PathVariable Long id ) {
        return Response.createResponse(HttpStatus.OK, personService.findById(id));
    }

    @DeleteMapping
    public void delete ( @RequestParam(required = true) Long id){
        personService.deleteById (id);
    }

    @PutMapping

    public ResponseEntity<Response> edit ( @Valid @RequestBody Person person,
                                           BindingResult result,
                                           @RequestParam(required = true) Long id ) {

        Person personDB = null;
        Person personCurrent;

        personDB = personService.findById(id);
        if (personDB == null) {
            throw new ResourceBadRequest("Person bad request", result);
        }
        personCurrent = new Person(person.getIdentification(),
                person.getName(), person.getLastname(),
                person.getEmail(), person.getPassword(),
                person.getMobile(), person.getAddress(),
                person.getRol(), person.getRFID());


        personDB.setIdentification(personCurrent.getIdentification());
        personDB.setName(personCurrent.getName());
        personDB.setLastname(personCurrent.getLastname());
        personDB.setEmail(personCurrent.getEmail());
        personDB.setPassword(personCurrent.getPassword());
        personDB.setMobile(personCurrent.getMobile());
        personDB.setAddress(personCurrent.getAddress());
        personDB.setRol(personCurrent.getRol());
        personDB.setRFID(personCurrent.getRFID());

        return Response.createResponse(HttpStatus.CREATED, personService.save(personDB));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Response> updatePartially(@PathVariable(name = "id") Long id,
                                                   @RequestBody PatchDTO dto) throws NotYetImplementedEx, NotFoundEx {
        // skipping validations for brevity
        if (dto.getOp().equalsIgnoreCase("update")) {
            boolean result = personService.partialUpdate(id, dto.getKey(), dto.getValue());
            return Response.createResponse(HttpStatus.ACCEPTED,result);
        } else {
            throw new NotYetImplementedEx("NOT_YET_IMPLEMENTED");
        }
    }
}


