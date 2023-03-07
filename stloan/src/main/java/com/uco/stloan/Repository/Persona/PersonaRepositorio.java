package com.uco.stloan.Repository.Persona;

import com.uco.stloan.model.articulo.Articulo;
import com.uco.stloan.model.persona.Persona;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonaRepositorio extends JpaRepository<Persona, Long> {
    @Query(value = "SELECT * FROM persona WHERE identificacion =:identificacion ", nativeQuery = true)
    Persona findByIdentification(@Param("identificacion") String identificacion);

    @Query(value = "DELETE FROM persona WHERE identificacion=?;", nativeQuery = true)
    void deleteByIdentification(String identificacion);
}
