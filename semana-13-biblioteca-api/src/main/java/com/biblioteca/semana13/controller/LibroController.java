package com.biblioteca.semana13.controller;

import com.biblioteca.semana13.entity.Libro;
import com.biblioteca.semana13.repository.LibroRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/libros")
public class LibroController {

    private final LibroRepository libroRepository;

    public LibroController(LibroRepository libroRepository) {
        this.libroRepository = libroRepository;
    }

    @GetMapping
    public ResponseEntity<List<Libro>> obtenerTodos() {
        List<Libro> libros = libroRepository.findAll();
        return ResponseEntity.ok(libros);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Libro> obtenerPorId(@PathVariable Long id) {
        Optional<Libro> libro = libroRepository.findById(id);
        return libro.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Libro> crearLibro(@RequestBody Libro libro) {
        Libro nuevoLibro = libroRepository.save(libro);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevoLibro);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Libro> actualizarLibro(@PathVariable Long id, @RequestBody Libro libroDetalles) {
        return libroRepository.findById(id)
                .map(libroExistente -> {
                    libroExistente.setCodigo(libroDetalles.getCodigo());
                    libroExistente.setTitulo(libroDetalles.getTitulo());
                    libroExistente.setAutor(libroDetalles.getAutor());
                    libroExistente.setCategoria(libroDetalles.getCategoria());
                    libroExistente.setAnioPublicacion(libroDetalles.getAnioPublicacion());
                    libroExistente.setPrecio(libroDetalles.getPrecio());
                    libroExistente.setDisponible(libroDetalles.getDisponible());
                    Libro libroActualizado = libroRepository.save(libroExistente);
                    return ResponseEntity.ok(libroActualizado);
                })
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarLibro(@PathVariable Long id) {
        if (libroRepository.existsById(id)) {
            libroRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/categoria/{categoria}")
    public ResponseEntity<List<Libro>> buscarPorCategoria(@PathVariable String categoria) {
        List<Libro> libros = libroRepository.findByCategoriaIgnoreCase(categoria);
        return ResponseEntity.ok(libros);
    }

    @GetMapping("/disponibles")
    public ResponseEntity<List<Libro>> obtenerDisponibles() {
        List<Libro> libros = libroRepository.findByDisponibleTrue();
        return ResponseEntity.ok(libros);
    }
}