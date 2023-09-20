package com.crud.demo.modeloDAO;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.crud.demo.modelo.Mascota;

@Repository
public interface IMascota extends CrudRepository<Mascota, Integer>{

}
