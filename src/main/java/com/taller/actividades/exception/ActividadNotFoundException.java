package com.taller.actividades.exception;

/** Excepcion cuando una Id no es encontrada
 * @author Todo el grupo
 */

public class ActividadNotFoundException extends RuntimeException {
    /** Constructor con el id no encontrado
     * @param id
     */
    public ActividadNotFoundException(Long id) {
        super("Actividad" + id + " no encontrada");
    }

}
