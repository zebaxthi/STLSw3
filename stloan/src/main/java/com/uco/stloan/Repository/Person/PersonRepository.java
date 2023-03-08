package com.uco.stloan.Repository.Person;

import com.uco.stloan.model.person.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonRepository extends JpaRepository<Person, Long> {
   /* @Query(value = "SELECT * FROM PEAPLE WHERE identificacion=? ", nativeQuery = true)
    Persona findByIdentification(String identificacion);

    @Query(value = "DELETE FROM PEAPLE WHERE identificacion=?;", nativeQuery = true)
    void deleteByIdentification(String identificacion);*/
}
