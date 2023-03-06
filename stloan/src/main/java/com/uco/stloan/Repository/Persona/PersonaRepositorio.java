package com.uco.stloan.Repository.Persona;

import com.uco.stloan.model.persona.Persona;
import org.springframework.data.jpa.repository.JpaRepository;


public interface PersonaRepositorio extends JpaRepository<Persona, String> {
}
