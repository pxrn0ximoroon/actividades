package com.taller.actividades.controller;

import com.taller.actividades.model.Actividad;
import com.taller.actividades.service.ActividadService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

/**Controlador MVC que maneja las peticiones HTTP para Actividad.
 * Este controlador se encarga de gestionar la navegacion entre las vistas del sistema
 * @author Todo el grupo
*/
@Controller
@RequestMapping("/actividades")
public class ActividadController {

    private final ActividadService service;

    /**Constructor con inyección de dependencias.
     * @param service servicio de actividades
     */
    public ActividadController(ActividadService service) {
        this.service = service;
    }

    /** Lista todas las actividades registradas.
     * @param model modelo para pasar datos a la vista
     * @return vista con la lista de actividades
     */
    @GetMapping
    public String listar(Model model) {
        model.addAttribute("actividades", service.listarTodas());
        return "actividades/lista";
    }

    /**Muestra formulario para crear nueva actividad.
     * @param model modelo pasar los datos a la vista
     * @return vista con el formulario vacio
     */
    @GetMapping("/nueva")
    public String nuevaForm(Model model) {
        model.addAttribute("actividad", new Actividad());
        return "actividades/formulario";
    }

    /**Guarda una actividad nueva.
     * @param actividad objeto recibido del formulario
     * @return redireccion a la lista de actividades
     */
    @PostMapping
    public String guardar(@ModelAttribute Actividad actividad) {
        service.guardar(actividad);
        return "redirect:/actividades";
    }

    /**Muestra el detalle de una actividad.
     * @param id identificador de la actividad
     * @param model modelo para pasar datos a la vista
     * @return vista con el detalle de la actividad
     */
    @GetMapping("/{id}")
    public String detalle(@PathVariable Long id, Model model) {
        service.buscarPorId(id)
                .ifPresent(a -> model.addAttribute("actividad", a));
        return "actividades/detalle";
    }

    /**Muestra formulario para editar una actividad existente.
     * @param id identifivador de actividad a editar
     * @param model modelo para pasar datos a la vista
     * @return vista con el formulario lleno
     */
    @GetMapping("/{id}/editar")
    public String editarForm(@PathVariable Long id, Model model) {
        service.buscarPorId(id)
                .ifPresent(a -> model.addAttribute("actividad", a));
        return "actividades/formulario";
    }

    /** Actualiza una actividad existente.
     * @param id identificador de actividad
     * @param actividad objeto con los datos actualizados
     * @return redireccion a la lista de actividades
     */
    @PostMapping("/{id}")
    public String actualizar(@PathVariable Long id,
                             @ModelAttribute Actividad actividad) {
        actividad.setId(id);
        service.guardar(actividad);
        return "redirect:/actividades";
    }

    /**Elimina una actividad por id.
     * @param id identificador de actividad a eliminar
     * @return redireccion a la lista de actividades
     */
    @PostMapping("/{id}/eliminar")
    public String eliminar(@PathVariable Long id) {
        service.eliminar(id);
        return "redirect:/actividades";
    }
}