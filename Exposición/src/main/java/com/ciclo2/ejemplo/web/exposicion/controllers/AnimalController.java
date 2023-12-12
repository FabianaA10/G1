package com.ciclo2.ejemplo.web.exposicion.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.ciclo2.ejemplo.web.exposicion.models.Animal;
import com.ciclo2.ejemplo.web.exposicion.models.EspecieAnimal;
import com.ciclo2.ejemplo.web.exposicion.models.HabilidadAnimal;
import com.ciclo2.ejemplo.web.exposicion.models.HabitatAnimal;
import com.ciclo2.ejemplo.web.exposicion.services.AnimalService;
import com.ciclo2.ejemplo.web.exposicion.services.EspecieAnimalService;
import com.ciclo2.ejemplo.web.exposicion.services.HabilidadAnimalService;
import com.ciclo2.ejemplo.web.exposicion.services.HabitatAnimalService;


@Controller
@RequestMapping("/animal")
public class AnimalController {

	@Autowired
	private AnimalService animalSer;

	@Autowired
	private EspecieAnimalService especieSer;
	
	@Autowired
	private HabitatAnimalService habitatSer;
	
	@Autowired
	private HabilidadAnimalService habilidadSer;

	@GetMapping("/listar")
	public String listar(Model mod) {
	    List<Animal> animales = animalSer.listar();

	    List<EspecieAnimal> especies = especieSer.listar();
	    
	    List<HabitatAnimal> habitats = habitatSer.listar();
	    
	    List<HabilidadAnimal> habilidades = habilidadSer.listar();

	    Animal animal = new Animal();

	    mod.addAttribute("ani", animales);
	    mod.addAttribute("especies", especies); 
	    mod.addAttribute("habitats", habitats);
	    mod.addAttribute("habilidades", habilidades);

	    mod.addAttribute("animal", animal);

	    return "animal/listar";
	}



	@PostMapping("/guardar")
	public String guardar(@ModelAttribute("animal") Animal animal, @RequestParam(name = "habilidades", required = false) List<Integer> habilidadesIds) {


	    EspecieAnimal especieAnimal = animal.getEspecieAnimal();
	    if (especieAnimal != null && especieAnimal.getId() == null) {
	        especieSer.guardar(especieAnimal);
	    }

	    HabitatAnimal habitatAnimal = animal.getHabitatAnimal();
	    if (habitatAnimal != null && habitatAnimal.getId() == null) {
	        habitatSer.guardar(habitatAnimal);
	    }
	    

	    animalSer.guardar(animal);

	    
	    return "redirect:/animal/listar";
	}



	@GetMapping("/eliminar/{id}")
	public String eliminar(@PathVariable Integer id) {
        animalSer.eliminar(id);
        return "redirect:/animal/listar?exito=eliminado";
    }

	@GetMapping("/editar/{id}")
	public String editar(@PathVariable Integer id, Model model) {
		Animal animal = animalSer.encontrar(id);
		List<EspecieAnimal> especies = especieSer.listar();
	    
	    List<HabitatAnimal> habitats = habitatSer.listar();
	    
	    List<HabilidadAnimal> habilidades  = habilidadSer.listar();
	    
		model.addAttribute("animal", animal);
		model.addAttribute("especies", especies); 
	    model.addAttribute("habitats", habitats);
	    model.addAttribute("habilidades", habilidades);
		return "animal/editar-animal";
	}

}
