package com.recetas.recetasapi.infrastructure.controller;

import com.recetas.recetasapi.application.dto.RecetaRequestDto;
import com.recetas.recetasapi.application.dto.RecetaResponseDto;
import com.recetas.recetasapi.application.usecase.RecetaUseCase;
import com.recetas.recetasapi.shared.constants.ApiConstants;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controlador REST para las operaciones CRUD de Receta.
 * Aplica SRP - solo maneja peticiones HTTP y respuestas.
 * Aplica DIP - depende de abstracción (RecetaUseCase).
 */
@RestController
@RequestMapping(ApiConstants.RECETAS_BASE_PATH)
@RequiredArgsConstructor
@Slf4j
public class RecetaController {

    private final RecetaUseCase recetaUseCase;

    @PostMapping
    public ResponseEntity<RecetaResponseDto> crearReceta(@Valid @RequestBody RecetaRequestDto requestDto) {
        log.info("Solicitud recibida para crear receta: {}", requestDto.getNombre());
        RecetaResponseDto responseDto = recetaUseCase.crearReceta(requestDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(responseDto);
    }

    @GetMapping("/{id}")
    public ResponseEntity<RecetaResponseDto> obtenerRecetaPorId(@PathVariable Long id) {
        log.info("Solicitud recibida para obtener receta con ID: {}", id);
        RecetaResponseDto responseDto = recetaUseCase.obtenerRecetaPorId(id);
        return ResponseEntity.ok(responseDto);
    }

    @GetMapping
    public ResponseEntity<List<RecetaResponseDto>> obtenerTodasLasRecetas() {
        log.info("Solicitud recibida para obtener todas las recetas");
        List<RecetaResponseDto> recetas = recetaUseCase.obtenerTodasLasRecetas();
        return ResponseEntity.ok(recetas);
    }

    @GetMapping("/buscar")
    public ResponseEntity<List<RecetaResponseDto>> buscarRecetasPorNombre(
            @RequestParam String keyword) {
        log.info("Solicitud recibida para buscar recetas con keyword: {}", keyword);
        List<RecetaResponseDto> recetas = recetaUseCase.buscarRecetasPorNombre(keyword);
        return ResponseEntity.ok(recetas);
    }

    @GetMapping("/categoria/{categoria}")
    public ResponseEntity<List<RecetaResponseDto>> obtenerRecetasPorCategoria(
            @PathVariable String categoria) {
        log.info("Solicitud recibida para obtener recetas de categoría: {}", categoria);
        List<RecetaResponseDto> recetas = recetaUseCase.obtenerRecetasPorCategoria(categoria);
        return ResponseEntity.ok(recetas);
    }

    @GetMapping("/dificultad/{dificultad}")
    public ResponseEntity<List<RecetaResponseDto>> obtenerRecetasPorDificultad(
            @PathVariable String dificultad) {
        log.info("Solicitud recibida para obtener recetas de dificultad: {}", dificultad);
        List<RecetaResponseDto> recetas = recetaUseCase.obtenerRecetasPorDificultad(dificultad);
        return ResponseEntity.ok(recetas);
    }

    @PutMapping("/{id}")
    public ResponseEntity<RecetaResponseDto> actualizarReceta(
            @PathVariable Long id,
            @Valid @RequestBody RecetaRequestDto requestDto) {
        log.info("Solicitud recibida para actualizar receta con ID: {}", id);
        RecetaResponseDto responseDto = recetaUseCase.actualizarReceta(id, requestDto);
        return ResponseEntity.ok(responseDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarReceta(@PathVariable Long id) {
        log.info("Solicitud recibida para eliminar receta con ID: {}", id);
        recetaUseCase.eliminarReceta(id);
        return ResponseEntity.noContent().build();
    }
}