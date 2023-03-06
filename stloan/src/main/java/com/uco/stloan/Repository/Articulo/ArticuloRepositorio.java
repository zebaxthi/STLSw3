package com.uco.stloan.Repository.Articulo;

import com.uco.stloan.model.articulo.Articulo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface ArticuloRepositorio extends JpaRepository<Articulo,String> {
}
