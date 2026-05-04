package pa.microservicios.controller;

import pa.microservicios.model.ActividadDTO;
import pa.microservicios.service.ActividadService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

/**
 * Controlador MVC que maneja las vistas Thymeleaf de actividades.
 * Gestiona la navegación entre páginas del sistema.
 *
 * @author Julian Andres - Andres Espinosa - Miguel David
 * @version 1.0
 * @since 2026
 */
@Controller
@RequestMapping("/actividades")
public class ActividadController {

    /** Servicio de actividades */
    private final ActividadService service;

    /**
     * Constructor con el servicio inyectado.
     *
     * @param service Servicio de actividades
     */
    public ActividadController(ActividadService service) {
        this.service = service;
    }

    /**
     * Muestra la lista de todas las actividades.
     *
     * @param model Modelo para pasar los datos a la vista
     * @return Vista actividades/lista.html
     */
    @GetMapping
    public String listar(Model model) {
        model.addAttribute("actividades", service.listarTodas());
        return "actividades/lista";
    }

    /**
     * Muestra el formulario para crear una actividad nueva.
     *
     * @param model Modelo con un objeto Actividad vacío
     * @return Vista actividades/formulario.html
     */
    @GetMapping("/nueva")
    public String nuevaForm(Model model) {
        model.addAttribute("actividad", new ActividadDTO());
        return "actividades/formulario";
    }

    /**
     * Guarda una actividad nueva desde el formulario.
     *
     * @param actividadDTO Datos enviados del formulario
     * @return Redirección a la lista de actividades
     */
    @PostMapping
    public String guardar(@ModelAttribute ActividadDTO actividadDTO) {
        service.guardar(actividadDTO);
        return "redirect:/actividades";
    }

    /**
     * Muestra el detalle de una actividad por su ID.
     *
     * @param id ID de la actividad
     * @param model Modelo con los datos de la actividad
     * @return Vista actividades/detalle.html
     */
    @GetMapping("/{id}")
    public String detalle(@PathVariable Long id, Model model) {
        service.buscarPorId(id)
                .ifPresent(a -> model.addAttribute("actividad", a));
        return "actividades/detalle";
    }

    /**
     * Muestra el formulario precargado para editar una actividad.
     *
     * @param id ID de la actividad a editar
     * @param model Modelo con los datos actuales de la actividad
     * @return Vista actividades/formulario.html con los datos cargados
     */
    @GetMapping("/{id}/editar")
    public String editarForm(@PathVariable Long id, Model model) {
        service.buscarPorId(id)
                .ifPresent(a -> model.addAttribute("actividad", a));
        return "actividades/formulario";
    }

    /**
     * Actualiza los datos de una actividad existente.
     *
     * @param id ID de la actividad
     * @param actividadDTO Datos actualizados del formulario
     * @return Redirección a la lista de actividades
     */
    @PostMapping("/{id}")
    public String actualizar(@PathVariable Long id,
                             @ModelAttribute ActividadDTO actividadDTO) {
        actividadDTO.setId(id);
        service.guardar(actividadDTO);
        return "redirect:/actividades";
    }

    /**
     * Elimina una actividad por su ID.
     *
     * @param id ID de la actividad a eliminar
     * @return Redirección a la lista de actividades
     */
    @PostMapping("/{id}/eliminar")
    public String eliminar(@PathVariable Long id) {
        service.eliminar(id);
        return "redirect:/actividades";
    }
}
