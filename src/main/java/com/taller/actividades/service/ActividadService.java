package com.taller.actividades.service;
import com.taller.actividades.exception.*;
import com.taller.actividades.model.ActividadResponse;
import com.taller.actividades.model.ActividadDTO;
import com.taller.actividades.model.Actividad;
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

    /**
     * Convierte un DTO recibido del frontend a la entidad Actividad.
     *
     * @param dto objeto recibido del frontend
     * @return entidad lista para persistir
     */
    private Actividad convertirDtoAEntidad(ActividadDTO dto) {
        return Actividad.builder()
                .titulo(dto.getTitulo())
                .descripcion(dto.getDescripcion())
                .fechaInicio(dto.getFechaInicio())
                .fechaTerminacion(dto.getFechaTerminacion())
                .tipoActividad(dto.getTipoActividad())
                .idQuehacer(dto.getIdQuehacer())
                .idTutor(dto.getIdTutor())
                .idHijo(dto.getIdHijo())
                .build();
    }

    /**
     * Convierte una entidad Actividad a un objeto de respuesta.
     *
     * @param actividad entidad recuperada de la base de datos
     * @return objeto de respuesta para enviar al frontend
     */
    private ActividadResponse convertirEntidadAResponse(Actividad actividad) {
        return ActividadResponse.builder()
                .id(actividad.getId())
                .titulo(actividad.getTitulo())
                .descripcion(actividad.getDescripcion())
                .fechaInicio(actividad.getFechaInicio())
                .fechaTerminacion(actividad.getFechaTerminacion())
                .tipoActividad(actividad.getTipoActividad())
                .idQuehacer(actividad.getIdQuehacer())
                .idTutor(actividad.getIdTutor())
                .idHijo(actividad.getIdHijo())
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
     * @param dto objeto recibido del frontend
     * @return actividad guardada como response
     */
    public ActividadResponse guardarDesdeDTO(ActividadDTO dto) {
        Actividad actividad = convertirDtoAEntidad(dto);
        return convertirEntidadAResponse(repository.save(actividad));
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
     * @param dto objeto con los datos nuevos
     * @return actividad actualizada como response
     */
    public Optional<ActividadResponse> actualizarDesdeDTO(Long id, ActividadDTO dto) {
        return repository.findById(id).map(a -> {
            a.setTitulo(dto.getTitulo());
            a.setDescripcion(dto.getDescripcion());
            a.setFechaInicio(dto.getFechaInicio());
            a.setFechaTerminacion(dto.getFechaTerminacion());
            a.setTipoActividad(dto.getTipoActividad());
            a.setIdQuehacer(dto.getIdQuehacer());
            a.setIdTutor(dto.getIdTutor());
            a.setIdHijo(dto.getIdHijo());
            return convertirEntidadAResponse(repository.save(a));
        });
    }
}
