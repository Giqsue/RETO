package com.crud.demo.controler;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.crud.demo.modelo.Mascota;
import com.crud.demo.serviceInterface.IMascotaService;


@Controller
@RequestMapping
public class MascotaControler {
	
	@Autowired
	private IMascotaService service;
	
	@GetMapping("/listar")
	public String listar(Model model) {
		List<Mascota>mascotas=service.listar();
		model.addAttribute("mascotas", mascotas);
		return "index";
	}
	@GetMapping("/new")
	public String agregar	(Model model) {
		model.addAttribute("mascota", new Mascota());
		return "form";
	}

	@PostMapping("/save")
	public String save(@Valid Mascota p,Model model) {
		int id=service.save(p);
		if(id==0) {
			return "form";
		}
		return "redirect:/listar";	
	}
	@GetMapping("/editar/{id}")
	public String editar(@PathVariable int id, Model model) {
		Optional<Mascota>mascota=service.listarId(id);
		model.addAttribute("mascota",mascota);
		return "form";
	}
	
	@GetMapping("/delete/{id}")
	public String eliminar(Model model,@PathVariable int id) {
		service.delete(id);
		return "redirect:/listar";
	}
}
