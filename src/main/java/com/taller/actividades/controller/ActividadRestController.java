package com.taller.actividades.controller;

import com.taller.actividades.model.Actividad;
import com.taller.actividades.service.ActividadService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//Controlador REST que expone los endpoints JSON para Actividad
@RestController
@RequestMapping("/api/actividades")
public class ActividadRestController {

    private final ActividadService service;

    //Constructor con inyeccion de dependencias.
    public ActividadRestController(ActividadService service) {
        this.service = service;
    }

    //Retorna todas las actividades en formato JSON.
    @GetMapping
    public List<Actividad> listarTodas() {
        return service.listarTodas();
    }

    //Retorna una actividad por su id.

    @GetMapping("/{id}")
    public ResponseEntity<Actividad> buscarPorId(@PathVariable Long id) {
        return service.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    //Crea una nueva actividad.
    @PostMapping
    public Actividad crear(@RequestBody Actividad actividad) {
        return service.guardar(actividad);
    }

    //Actualiza una actividad existente.
    @PutMapping("/{id}")
    public ResponseEntity<Actividad> actualizar(@PathVariable Long id,
                                                @RequestBody Actividad actividad) {
        return service.buscarPorId(id).map(a -> {
            actividad.setId(id);
            return ResponseEntity.ok(service.guardar(actividad));
        }).orElse(ResponseEntity.notFound().build());
    }

    //Elimina una actividad por su id.
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        service.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}