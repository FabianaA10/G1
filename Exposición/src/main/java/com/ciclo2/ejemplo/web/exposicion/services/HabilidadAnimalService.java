package com.ciclo2.ejemplo.web.exposicion.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ciclo2.ejemplo.web.exposicion.dao.HabilidadDao;
import com.ciclo2.ejemplo.web.exposicion.models.HabilidadAnimal;

;

@Service
public class HabilidadAnimalService {
	
	@Autowired
    private HabilidadDao habilidadDao;
	
	public List<HabilidadAnimal> listar() {
        return habilidadDao.findAll();
    }

    public void guardar(HabilidadAnimal habilidadAnimal) {
    	habilidadDao.save(habilidadAnimal);
    }

    public void eliminar(Integer id) {
    	habilidadDao.deleteById(id);
    }

    
    public HabilidadAnimal encontrar(Integer id) {
        return habilidadDao.findById(id).orElse(null);
    }

    public void eliminarHabilidades(List<HabilidadAnimal> habilidades) {
        // Eliminar cada habilidad
        for (HabilidadAnimal habilidad : habilidades) {
            habilidadDao.deleteById(habilidad.getId());
        }
    }

}
