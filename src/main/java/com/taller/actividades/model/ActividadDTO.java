package com.taller.actividades.model;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;

/**
 * Entidad que representa una actividad del hogar asignada a un hijo por un tutor.
 * Contiene la información necesaria para hacer seguimiento de cada tarea.
 * Esta es una clase de tipo DTO
 * @author Julian Andres - Andres Espinosa - Miguel David
 * @version 1.0
 * @since 2026
 */
@Entity
@Table(name = "actividades")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class ActividadDTO {

    /** Identificador único generado automáticamente */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /** Nombre de la actividad */
    private String titulo;

    /** Descripción detallada de lo que hay que hacer */
    private String descripcion;

    /** Fecha en que inicia la actividad */
    private LocalDate fechaInicio;

    /** Fecha límite para terminar la actividad */
    private LocalDate fechaTerminacion;

    /** Tipo de actividad: Fisica, Acompanamiento, Supervision, Creativa */
    private String tipoActividad;

    /** ID del quehacer del hogar al que pertenece */
    private Long idQuehacer;

    /** ID del tutor que asignó la tarea */
    private Long idTutor;

    /** ID del hijo al que se le asignó la tarea */
    private Long idHijo;
}
