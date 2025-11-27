package com.recetas.recetasapi.application.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * DTO para las respuestas de Receta.
 * Aplica SRP - solo representa datos de salida.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RecetaResponseDto {

    private Long id;
    private String nombre;
    private String ingredientes;
    private String instrucciones;
    private Integer tiempoPreparacion;
    private String dificultad;
    private Integer porciones;
    private String categoria;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime fechaCreacion;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime fechaActualizacion;
}