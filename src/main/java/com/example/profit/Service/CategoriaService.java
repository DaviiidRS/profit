package com.example.profit.Service;

import com.example.profit.Model.Categoria;
import com.example.profit.Repository.CategoriaRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoriaService {
    private final CategoriaRepository categoriaRepository;

    public CategoriaService(CategoriaRepository categoriaRepository) {
        this.categoriaRepository = categoriaRepository;
    }

    public Categoria guardar(Categoria categoria) {
        try {
            return categoriaRepository.save(categoria);
        } catch (Exception e) {
            throw new RuntimeException("Error al guardar la categoría " + e.getMessage(), e);
        }
    }

    public List<Categoria> listar(){
        try {
            return categoriaRepository.findAll();
        } catch (Exception e) {
            throw new RuntimeException("Error al listar las categorías " + e.getMessage(), e);
        }
    }

    public void eliminar(Long id_categoria){
        try {
            if (!categoriaRepository.existsById(id_categoria)) {
                throw new IllegalArgumentException("No se encontró una categoría con el ID " + id_categoria);
            }
            categoriaRepository.deleteById(id_categoria);
        } catch (Exception e) {
            throw new RuntimeException("Error al eliminar la categoría " + e.getMessage(), e);
        }
    }

    public Optional<Categoria> listarPorId(long id_categoria) {
        try {
            Optional<Categoria> categoria = categoriaRepository.findById(id_categoria);
            if (categoria.isEmpty()) {
                throw new IllegalArgumentException("Categoría con ID " + id_categoria + " no encontrado.");
            }
            return categoria;
        } catch (Exception e) {
            throw new RuntimeException("Error al buscar categoría por ID " + e.getMessage(), e);
        }
    }
}
