package com.papeleria.inventario.service;

import com.papeleria.inventario.exception.RecursoNoEncontradoException;
import com.papeleria.inventario.model.Categoria;
import com.papeleria.inventario.repository.CategoriaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoriaService {

    private final CategoriaRepository categoriaRepository;

    public CategoriaService(CategoriaRepository categoriaRepository) {
        this.categoriaRepository = categoriaRepository;
    }

    public List<Categoria> obtenerTodas() {
        return categoriaRepository.findAll();
    }

    public Categoria obtenerPorId(Long id) {
        return categoriaRepository.findById(id)
                .orElseThrow(() -> new RecursoNoEncontradoException("Categoría no encontrada con ID: " + id));
    }

    public Categoria guardar(Categoria categoria) {
        return categoriaRepository.save(categoria);
    }
}