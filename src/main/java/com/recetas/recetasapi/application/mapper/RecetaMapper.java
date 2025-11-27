package com.recetas.recetasapi.application.mapper;

import com.recetas.recetasapi.application.dto.RecetaRequestDto;
import com.recetas.recetasapi.application.dto.RecetaResponseDto;
import com.recetas.recetasapi.domain.model.Receta;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Mapper para convertir entre Entidad y DTOs.
 * Aplica SRP - solo se encarga de transformar objetos.
 */
@Component
public class RecetaMapper {

    public Receta toEntity(RecetaRequestDto dto) {
        if (dto == null) {
            return null;
        }

        return Receta.builder()
                .nombre(dto.getNombre())
                .ingredientes(dto.getIngredientes())
                .instrucciones(dto.getInstrucciones())
                .tiempoPreparacion(dto.getTiempoPreparacion())
                .dificultad(dto.getDificultad())
                .porciones(dto.getPorciones())
                .categoria(dto.getCategoria())
                .build();
    }

    public RecetaResponseDto toResponseDto(Receta receta) {
        if (receta == null) {
            return null;
        }

        return RecetaResponseDto.builder()
                .id(receta.getId())
                .nombre(receta.getNombre())
                .ingredientes(receta.getIngredientes())
                .instrucciones(receta.getInstrucciones())
                .tiempoPreparacion(receta.getTiempoPreparacion())
                .dificultad(receta.getDificultad())
                .porciones(receta.getPorciones())
                .categoria(receta.getCategoria())
                .fechaCreacion(receta.getFechaCreacion())
                .fechaActualizacion(receta.getFechaActualizacion())
                .build();
    }

    public List<RecetaResponseDto> toResponseDtoList(List<Receta> recetas) {
        if (recetas == null) {
            return List.of();
        }

        return recetas.stream()
                .map(this::toResponseDto)
                .toList();
    }

    public void updateEntityFromDto(RecetaRequestDto dto, Receta receta) {
        if (dto == null || receta == null) {
            return;
        }

        receta.setNombre(dto.getNombre());
        receta.setIngredientes(dto.getIngredientes());
        receta.setInstrucciones(dto.getInstrucciones());
        receta.setTiempoPreparacion(dto.getTiempoPreparacion());
        receta.setDificultad(dto.getDificultad());
        receta.setPorciones(dto.getPorciones());
        receta.setCategoria(dto.getCategoria());
    }
}