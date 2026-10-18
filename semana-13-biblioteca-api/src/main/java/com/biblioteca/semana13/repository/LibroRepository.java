package com.biblioteca.semana13.repository;

import com.biblioteca.semana13.entity.Libro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LibroRepository extends JpaRepository<Libro, Long> {

    List<Libro> findByCategoriaIgnoreCase(String categoria);

    List<Libro> findByDisponibleTrue();
}