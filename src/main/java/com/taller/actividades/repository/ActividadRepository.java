package com.taller.actividades.repository;

import com.taller.actividades.model.Actividad;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ActividadRepository extends JpaRepository<Actividad, Long> {
}
