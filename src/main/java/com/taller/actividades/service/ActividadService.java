package com.taller.actividades.service;

import com.taller.actividades.model.Actividad;
import com.taller.actividades.repository.ActividadRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

/**
 * Servicio que maneja la lógica de negocio de las actividades.
 * Se comunica con el repositorio para hacer las operaciones CRUD.
 *
 * @author Julian Andres - Andres Espinosa - Miguel David
 * @version 1.0
 * @since 2026
 */
@Service
public class ActividadService {

    /** Repositorio para acceder a la base de datos */
    private final ActividadRepository repository;

    /**
     * Constructor con el repositorio inyectado.
     *
     * @param repository Repositorio de actividades
     */
    public ActividadService(ActividadRepository repository) {
        this.repository = repository;
    }

    /**
     * Obtiene todas las actividades guardadas.
     *
     * @return Lista con todas las actividades
     */
    public List<Actividad> listarTodas() {
        return repository.findAll();
    }

    /**
     * Busca una actividad por su ID.
     *
     * @param id ID de la actividad
     * @return La actividad si existe, Optional vacío si no
     */
    public Optional<Actividad> buscarPorId(Long id) {
        return repository.findById(id);
    }

    /**
     * Guarda una actividad nueva o actualiza una existente.
     *
     * @param actividad Actividad a guardar
     * @return La actividad guardada con su ID
     */
    public Actividad guardar(Actividad actividad) {
        return repository.save(actividad);
    }

    /**
     * Elimina una actividad por su ID.
     *
     * @param id ID de la actividad a eliminar
     */
    public void eliminar(Long id) {
        repository.deleteById(id);
    }
}
