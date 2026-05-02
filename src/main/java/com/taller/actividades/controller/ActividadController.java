package com.taller.actividades.controller;

import com.taller.actividades.model.Actividad;
import com.taller.actividades.service.ActividadService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

//Controlador MVC que maneja las peticiones HTTP para Actividad.

@Controller
@RequestMapping("/actividades")
@CrossOrigin(origins = "*")
public class ActividadController {

    private final ActividadService service;

   //Constructor con inyección de dependencias.
    public ActividadController(ActividadService service) {
        this.service = service;
    }

    //Lista todas las actividades.
    @GetMapping
    public String listar(Model model) {
        model.addAttribute("actividades", service.listarTodas());
        return "actividades/lista";
    }

    //Muestra formulario para crear nueva actividad.
    @GetMapping("/nueva")
    public String nuevaForm(Model model) {
        model.addAttribute("actividad", new Actividad());
        return "actividades/formulario";
    }

    //Guarda una actividad nueva.
    @PostMapping
    public String guardar(@ModelAttribute Actividad actividad) {
        service.guardar(actividad);
        return "redirect:/actividades";
    }

    //Muestra el detalle de una actividad.
    @GetMapping("/{id}")
    public String detalle(@PathVariable Long id, Model model) {
        service.buscarPorId(id)
                .ifPresent(a -> model.addAttribute("actividad", a));
        return "actividades/detalle";
    }

    //Muestra formulario para editar una actividad existente.
    @GetMapping("/{id}/editar")
    public String editarForm(@PathVariable Long id, Model model) {
        service.buscarPorId(id)
                .ifPresent(a -> model.addAttribute("actividad", a));
        return "actividades/formulario";
    }

    // Actualiza una actividad existente.
    @PostMapping("/{id}")
    public String actualizar(@PathVariable Long id,
                             @ModelAttribute Actividad actividad) {
        actividad.setId(id);
        service.guardar(actividad);
        return "redirect:/actividades";
    }

    //Elimina una actividad por id.
    @PostMapping("/{id}/eliminar")
    public String eliminar(@PathVariable Long id) {
        service.eliminar(id);
        return "redirect:/actividades";
    }
}