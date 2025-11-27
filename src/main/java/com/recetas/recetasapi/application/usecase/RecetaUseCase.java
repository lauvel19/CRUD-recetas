package com.recetas.recetasapi.application.usecase;

import com.recetas.recetasapi.application.dto.RecetaRequestDto;
import com.recetas.recetasapi.application.dto.RecetaResponseDto;
import com.recetas.recetasapi.application.mapper.RecetaMapper;
import com.recetas.recetasapi.domain.model.Receta;
import com.recetas.recetasapi.domain.repository.RecetaRepository;
import com.recetas.recetasapi.domain.service.RecetaService;
import com.recetas.recetasapi.infrastructure.exception.RecetaNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Casos de uso de Receta (lógica de negocio).
 * Aplica SRP - solo maneja la lógica de negocio.
 * Aplica OCP - abierto a extensión (se pueden agregar nuevos casos de uso).
 * Aplica DIP - depende de abstracciones (RecetaRepository, RecetaService).
 */
@Service
@RequiredArgsConstructor
@Slf4j
@Transactional
public class RecetaUseCase implements RecetaService {

    private final RecetaRepository recetaRepository;
    private final RecetaMapper recetaMapper;

    @Override
    public RecetaResponseDto crearReceta(RecetaRequestDto requestDto) {
        log.info("Creando nueva receta: {}", requestDto.getNombre());

        Receta receta = recetaMapper.toEntity(requestDto);
        Receta recetaGuardada = recetaRepository.save(receta);

        log.info("Receta creada exitosamente con ID: {}", recetaGuardada.getId());
        return recetaMapper.toResponseDto(recetaGuardada);
    }

    @Transactional(readOnly = true)
    public RecetaResponseDto obtenerRecetaPorId(Long id) {
        log.info("Buscando receta con ID: {}", id);

        Receta receta = recetaRepository.findById(id)
                .orElseThrow(() -> new RecetaNotFoundException("Receta no encontrada con ID: " + id));

        return recetaMapper.toResponseDto(receta);
    }

    @Transactional(readOnly = true)
    public List<RecetaResponseDto> obtenerTodasLasRecetas() {
        log.info("Obteniendo todas las recetas");

        List<Receta> recetas = recetaRepository.findAll();
        return recetaMapper.toResponseDtoList(recetas);
    }

    @Transactional(readOnly = true)
    public List<RecetaResponseDto> buscarRecetasPorNombre(String keyword) {
        log.info("Buscando recetas con keyword: {}", keyword);

        List<Receta> recetas = recetaRepository.buscarPorNombre(keyword);
        return recetaMapper.toResponseDtoList(recetas);
    }

    @Transactional(readOnly = true)
    public List<RecetaResponseDto> obtenerRecetasPorCategoria(String categoria) {
        log.info("Buscando recetas de categoría: {}", categoria);

        List<Receta> recetas = recetaRepository.findByCategoria(categoria);
        return recetaMapper.toResponseDtoList(recetas);
    }

    @Transactional(readOnly = true)
    public List<RecetaResponseDto> obtenerRecetasPorDificultad(String dificultad) {
        log.info("Buscando recetas de dificultad: {}", dificultad);

        List<Receta> recetas = recetaRepository.findByDificultad(dificultad);
        return recetaMapper.toResponseDtoList(recetas);
    }

    public RecetaResponseDto actualizarReceta(Long id, RecetaRequestDto requestDto) {
        log.info("Actualizando receta con ID: {}", id);

        Receta receta = recetaRepository.findById(id)
                .orElseThrow(() -> new RecetaNotFoundException("Receta no encontrada con ID: " + id));

        recetaMapper.updateEntityFromDto(requestDto, receta);
        Receta recetaActualizada = recetaRepository.save(receta);

        log.info("Receta actualizada exitosamente con ID: {}", id);
        return recetaMapper.toResponseDto(recetaActualizada);
    }

    public void eliminarReceta(Long id) {
        log.info("Eliminando receta con ID: {}", id);

        if (!recetaRepository.existsById(id)) {
            throw new RecetaNotFoundException("Receta no encontrada con ID: " + id);
        }

        recetaRepository.deleteById(id);
        log.info("Receta eliminada exitosamente con ID: {}", id);
    }
}