package com.taller.actividades.repository;

import com.taller.actividades.model.Actividad;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repositorio para acceder a los datos de las actividades en la base de datos.
 * Hereda los métodos CRUD básicos de JpaRepository.
 *
 * @author Julian Andres - Andres Espinosa - Miguel David
 * @version 1.0
 * @since 2026
 */
@Repository
public interface ActividadRepository extends JpaRepository<Actividad, Long> {
}
