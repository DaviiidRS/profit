package com.example.profit.Service;

import com.example.profit.Model.Categoria;
import com.example.profit.Model.Objetivo;
import com.example.profit.Model.Receta;
import com.example.profit.Repository.CategoriaRepository;
import com.example.profit.Repository.ObjetivoRepository;
import com.example.profit.Repository.RecetaRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RecetaService {
    private final RecetaRepository recetaRepository;
    private final CategoriaRepository categoriaRepository;
    private final ObjetivoRepository objetivoRepository;

    public RecetaService(RecetaRepository recetaRepository,
                         CategoriaRepository categoriaRepository,
                         ObjetivoRepository objetivoRepository) {
        this.recetaRepository = recetaRepository;
        this.categoriaRepository = categoriaRepository;
        this.objetivoRepository = objetivoRepository;
    }

    public Receta guardar(Receta receta) {
        try {
            return recetaRepository.save(receta);
        } catch (Exception e) {
            throw new RuntimeException("Error al guardar la receta " + e.getMessage(), e);
        }
    }

    public List<Receta> listar(){
        try {
            return recetaRepository.findAll();
        } catch (Exception e) {
            throw new RuntimeException("Error al listar las recetas " + e.getMessage(), e);
        }
    }

    public void eliminar(Long id_receta){
        try {
            if (!recetaRepository.existsById(id_receta)) {
                throw new IllegalArgumentException("No se encontró una receta con el ID " + id_receta);
            }
            recetaRepository.deleteById(id_receta);
        } catch (Exception e) {
            throw new RuntimeException("Error al eliminar la receta " + e.getMessage(), e);
        }
    }

    public Optional<Receta> listarPorId(long id_receta) {
        try {
            Optional<Receta> receta = recetaRepository.findById(id_receta);
            if (receta.isEmpty()) {
                throw new IllegalArgumentException("Receta con ID " + id_receta + " no encontrado.");
            }
            return receta;
        } catch (Exception e) {
            throw new RuntimeException("Error al buscar receta por ID " + e.getMessage(), e);
        }
    }

    public Receta actualizar(Long id_receta, Receta receta) {
        if (!recetaRepository.existsById(id_receta)) {
            throw new IllegalArgumentException("No se encontró una receta con el id " + id_receta);
        }

        // Map id_categoria to Categoria entity
        if (receta.getId_categoria() != null) {
            Categoria categoria = categoriaRepository.findById(receta.getId_categoria())
                    .orElseThrow(() -> new IllegalArgumentException("No se encontró una categoría con el id " + receta.getId_categoria()));
            receta.setCategoria(categoria);
        }

        // Map id_objetivo to Objetivo entity
        if (receta.getId_objetivo() != null) {
            Objetivo objetivo = objetivoRepository.findById(receta.getId_objetivo())
                    .orElseThrow(() -> new IllegalArgumentException("No se encontró un objetivo con el id " + receta.getId_objetivo()));
            receta.setObjetivo(objetivo);
        }

        receta.setId_receta(id_receta); // Ensure the ID is set for the update
        return recetaRepository.save(receta);
    }

    public List<Receta> filtrarPorObjetivoId(Long id_objetivo) {
        if (id_objetivo == null) {
            return recetaRepository.findAll();
        } else {
            return recetaRepository.findByObjetivoId(id_objetivo);
        }
    }
}
