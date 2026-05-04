package com.taller.actividades.service;

import com.taller.actividades.mapper.ActividadMapper;
import com.taller.actividades.exception.*;
import com.taller.actividades.model.ActividadDTO;
import com.taller.actividades.model.ActividadResponse;
import com.taller.actividades.repository.ActividadRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
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

    /** Repositorio para acceder a la base de datos y mapper para interactuar con los archivos DTO */
    private final ActividadRepository repository;
    private final ActividadMapper mapper;

    /**
     * Constructor con el repositorio inyectado.
     * @param mapper
     * @param repository Repositorio de actividades
     */
    public ActividadService(ActividadRepository repository, ActividadMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    /**
     * Obtiene todas las actividades guardadas.
     *
     * @return Lista con todas las actividades
     */
    public List<ActividadDTO> listarTodas() {
        return repository.findAll();
    }

    /**
     * Busca una actividad por su ID.
     *
     * @param id ID de la actividad
     * @return La actividad si existe, Optional vacío si no
     */
    public Optional<ActividadDTO> buscarPorId(Long id) {
        return repository.findById(id);
    }

    /**
     * Guarda una actividad nueva o actualiza una existente.
     *
     * @param actividadDTO Actividad a guardar
     * @return La actividad guardada con su ID
     */
    public ActividadDTO guardar(ActividadDTO actividadDTO) {
        return repository.save(actividadDTO);
    }

    /**
     * Elimina una actividad por su ID.
     *
     * @param id ID de la actividad a eliminar
     */
    public void eliminar(Long id) {
        repository.deleteById(id);
    }


    /**
     * Retorna todas las actividades como lista de responses
     *
     * @return lista de ActividadResponse
     */
    public List<ActividadResponse> listarTodasComoResponse() {
        return repository.findAll()
                .stream()
                .map(mapper::toResponse)
                .collect(Collectors.toList());
    }

    /**
     * Guarda una actividad nueva recibida como DTO.
     *
     * @param actividadDTO objeto recibido del frontend
     * @return actividad guardada como response
     */
    public ActividadResponse guardarDesdeDTO(ActividadDTO actividadDTO) {
        return mapper.toResponse(repository.save(actividadDTO));
    }

    /**
     * Busca una actividad por id y la retorna como response
     *
     * @param id identificador de la actividad
     * @return Optional con el response o vacio si no existe
     */
    /*public Optional<ActividadResponse> buscarPorIdComoResponse(Long id) {
     return repository.findById(id)
              .map(this::convertirEntidadAResponse);
    }

     */
    public ActividadResponse buscarPorIdComoResponse(Long id) {
        return repository.findById(id)
                .map(mapper::toResponse)
                .orElseThrow(() -> new ActividadNotFoundException(id));
    }


    /**
     * Actualiza una actividad existente desde un DTO
     *
     * @param id identificador de la actividad
     * @param actividadDTO objeto con los datos nuevos
     * @return actividad actualizada como response
     */
    public Optional<ActividadResponse> actualizarDesdeDTO(Long id, ActividadDTO actividadDTO) {
        return repository.findById(id).map(a -> {
            a.setTitulo(actividadDTO.getTitulo());
            a.setDescripcion(actividadDTO.getDescripcion());
            a.setFechaInicio(actividadDTO.getFechaInicio());
            a.setFechaTerminacion(actividadDTO.getFechaTerminacion());
            a.setTipoActividad(actividadDTO.getTipoActividad());
            a.setIdQuehacer(actividadDTO.getIdQuehacer());
            a.setIdTutor(actividadDTO.getIdTutor());
            a.setIdHijo(actividadDTO.getIdHijo());
            return mapper.toResponse(repository.save(a));
        });
    }
}