package com.taller.actividades.model;


import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;
/**
 * Es una actividad del hogar asignada a un hijo por un tutor.
 * Contiene la informacion necesaria para hacer seguimiento de cada tarea.
 * @author Todo el grupo
 */
@Entity
@Table(name = "actividades")
@NoArgsConstructor  @AllArgsConstructor
@Getter  @Setter
@Builder
public class Actividad {
    /** Identificador unico generado automaticamente */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /** Nombre de la actividad */
    private String titulo;

    /** Descripccion */
    private String descripcion;

    /** Fecha en que inicia*/
    private LocalDate fechaInicio;

    /** Fecha en que termina*/
    private LocalDate fechaTerminacion;

    /** tipo de actividad*/
    private String tipoActividad;

    /** Que se va a hacer */
    private Long idQuehacer;

    /** identificador del tutor que asigno la tarea */
    private Long idTutor;

    /** identificador del hijo al que se le asigno la tarea */
    private Long idHijo;
}
