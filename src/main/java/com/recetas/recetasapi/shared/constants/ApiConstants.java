package com.recetas.recetasapi.shared.constants;

/**
 * Constantes de la API.
 * Evita el antipatrón de magic strings.
 */
public final class ApiConstants {

    private ApiConstants() {
        throw new UnsupportedOperationException("Esta es una clase de utilidad y no puede ser instanciada");
    }

    public static final String API_VERSION = "/api/v1";
    public static final String RECETAS_BASE_PATH = API_VERSION + "/recetas";
}