package com.taller.actividades.controller;

import com.taller.actividades.model.Actividad;
import com.taller.actividades.service.ActividadService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controlador REST para gestionar actividades desde el frontend.
 * Expone los endpoints CRUD en formato JSON.
 * 
 * <p>Endpoints:</p>
 * <ul>
 *   <li>GET /api/actividades - Lista todas las actividades</li>
 *   <li>GET /api/actividades/{id} - Busca una actividad por ID</li>
 *   <li>POST /api/actividades - Crea una nueva actividad</li>
 *   <li>PUT /api/actividades/{id} - Modifica una actividad</li>
 *   <li>DELETE /api/actividades/{id} - Elimina una actividad</li>
 * </ul>
 *
 * @author Julian Andres - Andres Espinosa - Miguel David
 * @version 1.0
 * @since 2026
 */
@RestController
@RequestMapping("/api/actividades")
public class ActividadRestController {

    /** Servicio que contiene la lógica de negocio de actividades */
    private final ActividadService service;

    /**
     * Constructor que recibe el servicio por inyección de dependencias.
     *
     * @param service Servicio de actividades
     */
    public ActividadRestController(ActividadService service) {
        this.service = service;
    }

    /**
     * Lista todas las actividades guardadas.
     *
     * @return Lista de actividades en formato JSON
     */
    @GetMapping
    public List<Actividad> listarTodas() {
        return service.listarTodas();
    }

    /**
     * Busca una actividad por su ID.
     *
     * @param id ID de la actividad a buscar
     * @return La actividad encontrada o 404 si no existe
     */
    @GetMapping("/{id}")
    public ResponseEntity<Actividad> buscarPorId(@PathVariable Long id) {
        return service.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    /**
     * Crea una nueva actividad con los datos enviados en el cuerpo.
     *
     * @param actividad Datos de la actividad en JSON
     * @return La actividad creada con su ID asignado
     */
    @PostMapping
    public Actividad crear(@RequestBody Actividad actividad) {
        return service.guardar(actividad);
    }

    /**
     * Actualiza todos los datos de una actividad existente.
     *
     * @param id ID de la actividad a modificar
     * @param actividad Nuevos datos de la actividad en JSON
     * @return La actividad actualizada o 404 si no existe
     */
    @PutMapping("/{id}")
    public ResponseEntity<Actividad> actualizar(@PathVariable Long id,
                                                @RequestBody Actividad actividad) {
        return service.buscarPorId(id).map(a -> {
            actividad.setId(id);
            return ResponseEntity.ok(service.guardar(actividad));
        }).orElse(ResponseEntity.notFound().build());
    }

    /**
     * Elimina una actividad por su ID.
     *
     * @param id ID de la actividad a eliminar
     * @return 204 No Content al eliminar correctamente
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        service.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}
