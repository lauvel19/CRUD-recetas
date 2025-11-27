package com.recetas.recetasapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Clase principal de la aplicación Spring Boot.
 * Punto de entrada de la aplicación de gestión de recetas de cocina.
 */
@SpringBootApplication
public class RecetasApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(RecetasApiApplication.class, args);
        System.out.println("\n===========================================");
        System.out.println("🍳 API de Recetas de Cocina iniciada");
        System.out.println("📚 Documentación: http://localhost:8080");
        System.out.println("🔗 API Base: http://localhost:8080/api/v1/recetas");
        System.out.println("===========================================\n");
    }
}