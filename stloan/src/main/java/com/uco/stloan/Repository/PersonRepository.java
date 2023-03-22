package com.uco.stloan.Repository;

import com.uco.stloan.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonRepository extends JpaRepository<Person, Long> {
   @Query(value = "SELECT * FROM PERSONS WHERE email=? ", nativeQuery = true)
    Person findByEmail(String email);
}
