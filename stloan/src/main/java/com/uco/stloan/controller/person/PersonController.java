package com.uco.stloan.controller.person;

import com.uco.stloan.Services.Persona.PersonService;
import com.uco.stloan.dto.PatchDto;
import com.uco.stloan.exception.NotFoundEx;
import com.uco.stloan.exception.NotYetImplementedEx;
import com.uco.stloan.model.person.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/v1/rest/personas")
public class PersonController {
    @Autowired
    private PersonService personService;

    @GetMapping
    public  ResponseEntity<?> listPerson() {
        List<Person> person = personService.findAll();
        return new ResponseEntity<>(person, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Person> create(@Valid @RequestBody Person person) {
        return new ResponseEntity<>(personService.save(person), HttpStatus.OK);
    }

    @DeleteMapping
    public void delete ( @RequestParam(required = true) String idPersona){
     personService.deleteById (idPersona);
    }

    @PutMapping
    public ResponseEntity<Person> edit(@Valid @RequestBody Person person,
                                       @RequestParam(required = true) String idPersona ){

        Person personDB = null;
        Person personCurrent;

        personDB = personService.findById(idPersona);
        if(personDB == null){
            return new ResponseEntity<>(personService.findById(idPersona), HttpStatus.BAD_REQUEST);
        }
        personCurrent = new Person(person.getIdentification(),
                person.getName(), person.getLastname(),
                person.getEmail(), person.getPassword(),
                person.getCellular(), person.getAddress(),
        person.getRol(), person.getCodeRFID());

        //empleadoDB.setNombre(empleadoCurrent.getNombre());
        personDB.setIdentification(personCurrent.getIdentification());
        personDB.setName(personCurrent.getName());
        personDB.setLastname(personCurrent.getLastname());
        personDB.setEmail(personCurrent.getEmail());
        personDB.setPassword(personCurrent.getPassword());
        personDB.setCellular(personCurrent.getCellular());
        personDB.setAddress(personCurrent.getAddress());
        personDB.setRol(personCurrent.getRol());
        personDB.setCodeRFID(personCurrent.getCodeRFID());




        personDB = personService.save(personDB);
        return new ResponseEntity<>(person, HttpStatus.CREATED);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Boolean> updatePartially(@PathVariable(name = "id") int id,
                                                   @RequestBody PatchDto dto) throws NotYetImplementedEx, NotFoundEx {
        // skipping validations for brevity
        if (dto.getOp().equalsIgnoreCase("update")) {
            boolean result = personService.partialUpdate(id, dto.getKey(), dto.getValue());
            return new ResponseEntity<>(result, HttpStatus.ACCEPTED);
        } else {
            throw new NotYetImplementedEx("NOT_YET_IMPLEMENTED");
        }
    }


}
