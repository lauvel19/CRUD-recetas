package com.recetas.recetasapi.domain.service;

import com.recetas.recetasapi.application.dto.RecetaRequestDto;
import com.recetas.recetasapi.application.dto.RecetaResponseDto;

import java.util.List;

/**
 * Interfaz de servicio para operaciones de Receta.
 * Aplica ISP (Interface Segregation Principle) - interfaz específica para servicios de receta.
 * Aplica DIP (Dependency Inversion Principle) - define el contrato, no la implementación.
 */
public interface RecetaService {

    RecetaResponseDto crearReceta(RecetaRequestDto requestDto);

    RecetaResponseDto obtenerRecetaPorId(Long id);

    List<RecetaResponseDto> obtenerTodasLasRecetas();

    List<RecetaResponseDto> buscarRecetasPorNombre(String keyword);

    List<RecetaResponseDto> obtenerRecetasPorCategoria(String categoria);

    List<RecetaResponseDto> obtenerRecetasPorDificultad(String dificultad);

    RecetaResponseDto actualizarReceta(Long id, RecetaRequestDto requestDto);

    void eliminarReceta(Long id);
}