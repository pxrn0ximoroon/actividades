package com.taller.actividades.model;


import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;

@Entity
@Table(name = "actividades")
@NoArgsConstructor  @AllArgsConstructor
@Getter  @Setter
@Builder
public class Actividad {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String titulo;

    private String descripcion;

    private LocalDate fechaInicio;

    private LocalDate fechaTerminacion;

    private String tipoActividad;

    private Long idQuehacer;

    private Long idTutor;

    private Long idHijo;
}