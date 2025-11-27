package com.recetas.recetasapi.domain.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * Entidad de dominio que representa una Receta de cocina.
 * Aplica SRP (Single Responsibility Principle) - solo representa el modelo de datos.
 */
@Entity
@Table(name = "recetas")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Receta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 200)
    private String nombre;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String ingredientes;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String instrucciones;

    @Column(nullable = false)
    private Integer tiempoPreparacion; // en minutos

    @Column(nullable = false)
    private String dificultad; // Fácil, Media, Difícil

    @Column(nullable = false)
    private Integer porciones;

    @Column(length = 100)
    private String categoria; // Desayuno, Almuerzo, Cena, Postre, etc.

    @Column(name = "fecha_creacion", nullable = false, updatable = false)
    private LocalDateTime fechaCreacion;

    @Column(name = "fecha_actualizacion")
    private LocalDateTime fechaActualizacion;

    @PrePersist
    protected void onCreate() {
        fechaCreacion = LocalDateTime.now();
        fechaActualizacion = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        fechaActualizacion = LocalDateTime.now();
    }
}