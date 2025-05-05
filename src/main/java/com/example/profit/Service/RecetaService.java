package com.example.profit.Service;

import com.example.profit.Model.Receta;
import com.example.profit.Repository.RecetaRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RecetaService {
    private final RecetaRepository recetaRepository;

    public RecetaService(RecetaRepository recetaRepository) {
        this.recetaRepository = recetaRepository;
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
}
