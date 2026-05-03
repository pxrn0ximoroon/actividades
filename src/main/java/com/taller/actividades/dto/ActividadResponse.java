package com.taller.actividades.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

/** Objeto de respuesta que se envia al frontend tras una peticion
 * controla que datos son expuestos por la API-REST
 * @author Todo el grupo
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ActividadResponse {
    /**Id de la actividad (unico)     */
    private Long id;
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
