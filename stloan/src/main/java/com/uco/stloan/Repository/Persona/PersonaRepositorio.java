package com.uco.stloan.Repository.Persona;

import com.uco.stloan.model.articulo.Articulo;
import com.uco.stloan.model.persona.Persona;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonaRepositorio extends JpaRepository<Persona, Long> {
   /* @Query(value = "SELECT * FROM PEAPLE WHERE identificacion=? ", nativeQuery = true)
    Persona findByIdentification(String identificacion);

    @Query(value = "DELETE FROM PEAPLE WHERE identificacion=?;", nativeQuery = true)
    void deleteByIdentification(String identificacion);*/
}
