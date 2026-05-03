package com.taller.actividades.model;

import lombok.*;

import java.time.LocalDate;

/** Archivo de transferencia de datos para recibir peticiones desde el frontend
 * @author Todo el grupo
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ActividadDTO {
    /** Titulo de la actividad     */
    private String titulo;
    /** Descripcion actividad     */
    private String descripcion;
    /** Fecha inicio actividad     */
    private LocalDate fechaInicio;
    /** Fecha fin actividad     */
    private LocalDate fechaTerminacion;
    /** Tipo de actividad     */
    private String tipoActividad;
    /** id QueHacer     */
    private Long idQuehacer;
    /** Id padre     */
    private Long idTutor;
    /** Id hijo     */
    private Long idHijo;

}
