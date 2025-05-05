package com.example.profit.Service;

import com.example.profit.Model.RecetaIngrediente;
import com.example.profit.Repository.RecetaIngredienteRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RecetaIngredienteService {
    private final RecetaIngredienteRepository recetaIngredienteRepository;

    public RecetaIngredienteService(RecetaIngredienteRepository recetaIngredienteRepository) {
        this.recetaIngredienteRepository = recetaIngredienteRepository;
    }

    public RecetaIngrediente guardar(RecetaIngrediente recetaIngrediente) {
        try {
            return recetaIngredienteRepository.save(recetaIngrediente);
        } catch (Exception e) {
            throw new RuntimeException("Error al guardar el ingrediente de la receta " + e.getMessage(), e);
        }
    }

    public List<RecetaIngrediente> listar(){
        try {
            return recetaIngredienteRepository.findAll();
        } catch (Exception e) {
            throw new RuntimeException("Error al listar los ingredientes de las recetas " + e.getMessage(), e);
        }
    }

    public void eliminar(Long id_recetaIngrediente){
        try {
            if (!recetaIngredienteRepository.existsById(id_recetaIngrediente)) {
                throw new IllegalArgumentException("No se encontró el ID " + id_recetaIngrediente);
            }
            recetaIngredienteRepository.deleteById(id_recetaIngrediente);
        } catch (Exception e) {
            throw new RuntimeException("Error al eliminar " + e.getMessage(), e);
        }
    }

    public Optional<RecetaIngrediente> listarPorId(long id_recetaIngrediente) {
        try {
            Optional<RecetaIngrediente> recetaIngrediente = recetaIngredienteRepository.findById(id_recetaIngrediente);
            if (recetaIngrediente.isEmpty()) {
                throw new IllegalArgumentException("ID " + id_recetaIngrediente + " no encontrado.");
            }
            return recetaIngrediente;
        } catch (Exception e) {
            throw new RuntimeException("Error al buscar por ID " + e.getMessage(), e);
        }
    }
}
