package com.uco.stloan.Repository.Articulo;

import com.uco.stloan.model.articulo.Articulo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ArticuloRepositorio extends JpaRepository<Articulo,String> {
   /* @Query(value = "SELECT * FROM ARTICLES WHERE ref=?",nativeQuery = true)
    public Articulo findByRef(String ref);

    @Query(value = "DELETE FROM ARTICLES WHERE ref=?;",nativeQuery = true)
    public void deleteByRef(String ref);*/
}
