package com.crud.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.crud.demo.modelo.Mascota;
import com.crud.demo.modeloDAO.IMascota;
import com.crud.demo.serviceInterface.IMascotaService;

@Service
public class MascotaService implements IMascotaService {

	@Autowired
	private IMascota dao;
	
	@Override
	public List<Mascota> listar() {		
		return (List<Mascota>) dao.findAll();
	}	

	@Override
	public Optional<Mascota> listarId(int id) {		
		return dao.findById(id);
	}

	@Override
	public int save(Mascota p) {
		int res=0;
		Mascota per=dao.save(p);
		if(!per.equals(null)) {
			res=1;
		}
		return res;
	}

	@Override
	public void delete(int id) {
		dao.deleteById(id);
		
	}

}
