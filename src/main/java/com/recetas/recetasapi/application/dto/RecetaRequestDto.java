package com.recetas.recetasapi.application.dto;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * DTO para las peticiones de creación/actualización de Receta.
 * Aplica SRP - solo representa datos de entrada.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RecetaRequestDto {

    @NotBlank(message = "El nombre es obligatorio")
    @Size(min = 3, max = 200, message = "El nombre debe tener entre 3 y 200 caracteres")
    private String nombre;

    @NotBlank(message = "Los ingredientes son obligatorios")
    @Size(min = 10, message = "Los ingredientes deben tener al menos 10 caracteres")
    private String ingredientes;

    @NotBlank(message = "Las instrucciones son obligatorias")
    @Size(min = 20, message = "Las instrucciones deben tener al menos 20 caracteres")
    private String instrucciones;

    @NotNull(message = "El tiempo de preparación es obligatorio")
    @Min(value = 1, message = "El tiempo de preparación debe ser al menos 1 minuto")
    @Max(value = 1440, message = "El tiempo de preparación no puede superar 1440 minutos (24 horas)")
    private Integer tiempoPreparacion;

    @NotBlank(message = "La dificultad es obligatoria")
    @Pattern(regexp = "^(Fácil|Media|Difícil)$", message = "La dificultad debe ser: Fácil, Media o Difícil")
    private String dificultad;

    @NotNull(message = "El número de porciones es obligatorio")
    @Min(value = 1, message = "Debe haber al menos 1 porción")
    @Max(value = 100, message = "No puede haber más de 100 porciones")
    private Integer porciones;

    @NotBlank(message = "La categoría es obligatoria")
    @Size(min = 3, max = 100, message = "La categoría debe tener entre 3 y 100 caracteres")
    private String categoria;
}