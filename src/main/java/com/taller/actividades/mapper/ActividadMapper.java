package com.taller.actividades.mapper;

import com.taller.actividades.model.ActividadDTO;
import com.taller.actividades.model.ActividadResponse;
import org.springframework.stereotype.Component;

/**
 * Clase encargada de convertir entre la entidad y el objeto de respuesta.
 * @author Julian Andres - Andres Espinosa - Miguel David
 * @version 1.0
 * @since 2026
 */
@Component
public class ActividadMapper {

    /**
     * Convierte una entidad ActividadDTO a ActividadResponse.
     * @param dto entidad recuperada de la base de datos
     * @return objeto de respuesta para enviar al frontend
     */
    public ActividadResponse toResponse(ActividadDTO dto) {
        return ActividadResponse.builder()
                .id(dto.getId())
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
}