package com.recetas.recetasapi.infrastructure.exception;

/**
 * Excepción personalizada para receta no encontrada.
 * Aplica SRP - solo representa una excepción específica.
 */
public class RecetaNotFoundException extends RuntimeException {

    public RecetaNotFoundException(String message) {
        super(message);
    }

    public RecetaNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}