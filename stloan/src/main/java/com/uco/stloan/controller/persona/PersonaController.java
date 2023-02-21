package com.uco.stloan.controller.persona;

import com.uco.stloan.domain.persona.Persona;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1/rest")
public class PersonaController {
    /*@Autowired
    private BookService bookService;*/

    @GetMapping("/persona")
    public Flux<Persona> get() {

        return null;/*bookService.get();*/
    }

    @PostMapping("/personas")
    public Mono<Persona> create( @Valid @RequestBody Persona persona) {
        return null;/*bookService.save(book);*/
    }

    @DeleteMapping("/personas")
    public Mono<ResponseEntity<Void>> delete ( @RequestParam(required = true) String idPersona){

        return null; /*bookService.delete(idBook).then(Mono.just(new ResponseEntity<Void>(HttpStatus.OK)))
                .defaultIfEmpty(new ResponseEntity<Void>(HttpStatus.NOT_FOUND));*/

    }

    @PutMapping("/persona")
    public Mono<Persona> edit(@Valid @RequestBody Persona persona,
                              @RequestParam(required = true) String idPersona ){
        return null;
    }

}
