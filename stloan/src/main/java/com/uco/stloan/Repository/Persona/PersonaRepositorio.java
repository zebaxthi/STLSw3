package com.uco.stloan.Repository.Persona;

import com.uco.stloan.model.persona.Persona;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface PersonaRepositorio extends JpaRepository<Persona, String> {
}
