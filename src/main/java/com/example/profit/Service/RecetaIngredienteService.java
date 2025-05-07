package com.example.profit.Service;

import com.example.profit.Model.Ingrediente;
import com.example.profit.Model.Receta;
import com.example.profit.Model.RecetaIngrediente;
import com.example.profit.Repository.IngredienteRepository;
import com.example.profit.Repository.RecetaIngredienteRepository;
import com.example.profit.Repository.RecetaRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RecetaIngredienteService {
    private final RecetaIngredienteRepository recetaIngredienteRepository;
    private final RecetaRepository recetaRepository;
    private final IngredienteRepository ingredienteRepository;

    public RecetaIngredienteService(RecetaIngredienteRepository recetaIngredienteRepository,
                                    RecetaRepository recetaRepository,
                                    IngredienteRepository ingredienteRepository) {
        this.recetaIngredienteRepository = recetaIngredienteRepository;
        this.recetaRepository = recetaRepository;
        this.ingredienteRepository = ingredienteRepository;
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
    public RecetaIngrediente actualizar(Long id_recetaIngrediente, RecetaIngrediente recetaIngrediente) {
        if (!recetaIngredienteRepository.existsById(id_recetaIngrediente)) {
            throw new IllegalArgumentException("No se encontró el ID " + id_recetaIngrediente);
        }
        //mapear la receta y el ingrediente
        if (recetaIngrediente.getId_receta() != null) {
            Receta receta = recetaRepository.findById(recetaIngrediente.getId_receta())
                    .orElseThrow(() -> new IllegalArgumentException("No se encontró una receta con el ID " + recetaIngrediente.getId_receta()));
            recetaIngrediente.setReceta(receta);
        }
        //mapear el ingrediente
        if (recetaIngrediente.getId_ingrediente() != null) {
            Ingrediente ingrediente = ingredienteRepository.findById(recetaIngrediente.getId_ingrediente())
                    .orElseThrow(() -> new IllegalArgumentException("No se encontró un ingrediente con el ID " + recetaIngrediente.getId_ingrediente()));
            recetaIngrediente.setIngrediente(ingrediente);
        }

        recetaIngrediente.setId_recetaIngrediente(id_recetaIngrediente);
        return recetaIngredienteRepository.save(recetaIngrediente);
    }
}
