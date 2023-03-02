package com.uco.stloan.Services.Persona;

import com.uco.stloan.model.persona.Persona;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class PersonaImpl implements PersonaService {

    @Override
    @Transactional(readOnly = true)
    public List<Persona> findAll ( ) {
        return null;
    }

    @Override
    @Transactional(readOnly = true)
    public Persona findById ( String identificacion ) {
        return null;
    }

    @Override
    @Transactional
    public Persona save ( Persona persona ) {
        return new Persona();
    }

    @Override
    @Transactional
    public void deleteById ( String identificacion ) {

    }
}
