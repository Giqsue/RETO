package com.crud.demo.serviceInterface;

import java.util.List;
import java.util.Optional;

import com.crud.demo.modelo.Mascota;


public interface IMascotaService {
	public List<Mascota> listar();

	public Optional<Mascota> listarId(int id);

	public int save(Mascota m);

	public void delete(int id);
}
