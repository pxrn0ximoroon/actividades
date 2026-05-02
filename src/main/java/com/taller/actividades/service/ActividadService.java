package com.taller.actividades.service;

import com.taller.actividades.model.Actividad;
import com.taller.actividades.repository.ActividadRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;


@Service
public class ActividadService {

    private final ActividadRepository repository;


    public ActividadService(ActividadRepository repository) {
        this.repository = repository;
    }

    public List<Actividad> listarTodas() {
        return repository.findAll();
    }

    public Optional<Actividad> buscarPorId(Long id) {
        return repository.findById(id);
    }

    public Actividad guardar(Actividad actividad) {
        return repository.save(actividad);
    }

    public void eliminar(Long id) {
        repository.deleteById(id);
    }
}