package com.recetas.recetasapi.domain.repository;

import com.recetas.recetasapi.domain.model.Receta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Repositorio para operaciones de persistencia de Receta.
 * Aplica ISP (Interface Segregation Principle) - interfaz específica para Receta.
 * Aplica DIP (Dependency Inversion Principle) - depende de abstracción no de implementación.
 */
@Repository
public interface RecetaRepository extends JpaRepository<Receta, Long> {

    Optional<Receta> findByNombreIgnoreCase(String nombre);

    List<Receta> findByCategoria(String categoria);

    List<Receta> findByDificultad(String dificultad);

    @Query("SELECT r FROM Receta r WHERE LOWER(r.nombre) LIKE LOWER(CONCAT('%', :keyword, '%'))")
    List<Receta> buscarPorNombre(@Param("keyword") String keyword);
}