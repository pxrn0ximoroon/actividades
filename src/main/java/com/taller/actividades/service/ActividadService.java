package com.taller.actividades.service;
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
     * Convierte un DTO recibido del frontend a la entidad Actividad.
     *
     * @param dto objeto recibido del frontend
     * @return entidad lista para persistir
     */

    /**
     * Convierte una entidad Actividad a un objeto de respuesta.
     *
     * @param actividadDTO entidad recuperada de la base de datos
     * @return objeto de respuesta para enviar al frontend
     */
    private ActividadResponse convertirEntidadAResponse(ActividadDTO actividadDTO) {
        return ActividadResponse.builder()
                .id(actividadDTO.getId())
                .titulo(actividadDTO.getTitulo())
                .descripcion(actividadDTO.getDescripcion())
                .fechaInicio(actividadDTO.getFechaInicio())
                .fechaTerminacion(actividadDTO.getFechaTerminacion())
                .tipoActividad(actividadDTO.getTipoActividad())
                .idQuehacer(actividadDTO.getIdQuehacer())
                .idTutor(actividadDTO.getIdTutor())
                .idHijo(actividadDTO.getIdHijo())
                .build();
    }

    /**
     * Retorna todas las actividades como lista de responses
     *
     * @return lista de ActividadResponse
     */
    public List<ActividadResponse> listarTodasComoResponse() {
        return repository.findAll()
                .stream()
                .map(this::convertirEntidadAResponse)
                .collect(Collectors.toList());
    }

    /**
     * Guarda una actividad nueva recibida como DTO.
     *
     * @param actividadDTO objeto recibido del frontend
     * @return actividad guardada como response
     */
    public ActividadResponse guardarDesdeDTO(ActividadDTO actividadDTO) {
        return convertirEntidadAResponse(repository.save(actividadDTO));
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
                .map(this::convertirEntidadAResponse)
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
            return convertirEntidadAResponse(repository.save(a));
        });
    }
}
