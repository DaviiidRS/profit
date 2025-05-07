package com.example.profit.Service;

import com.example.profit.Model.Ingrediente;
import com.example.profit.Repository.IngredienteRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class IngredienteService {
    private final IngredienteRepository ingredienteRepository;

    public IngredienteService(IngredienteRepository ingredienteRepository) {
        this.ingredienteRepository = ingredienteRepository;
    }

    public Ingrediente guardar(Ingrediente ingrediente) {
        try {
            return ingredienteRepository.save(ingrediente);
        } catch (Exception e) {
            throw new RuntimeException("Error al guardar el ingrediente " + e.getMessage(), e);
        }
    }

    public List<Ingrediente> listar(){
        try {
            return ingredienteRepository.findAll();
        } catch (Exception e) {
            throw new RuntimeException("Error al listar los ingredientes " + e.getMessage(), e);
        }
    }

    public void eliminar(Long id_ingrediente){
        try {
            if (!ingredienteRepository.existsById(id_ingrediente)) {
                throw new IllegalArgumentException("No se encontró un ingrediente con el ID " + id_ingrediente);
            }
            ingredienteRepository.deleteById(id_ingrediente);
        } catch (Exception e) {
            throw new RuntimeException("Error al eliminar el ingrediente " + e.getMessage(), e);
        }
    }

    public Optional<Ingrediente> listarPorId(long id_ingrediente) {
        try {
            Optional<Ingrediente> ingrediente = ingredienteRepository.findById(id_ingrediente);
            if (ingrediente.isEmpty()) {
                throw new IllegalArgumentException("Ingrediente con ID " + id_ingrediente + " no encontrado.");
            }
            return ingrediente;
        } catch (Exception e) {
            throw new RuntimeException("Error al buscar ingrediente por ID " + e.getMessage(), e);
        }
    }
    public Ingrediente actualizar(Long id_ingrediente, Ingrediente ingrediente) {
        if (!ingredienteRepository.existsById(id_ingrediente)) {
            throw new IllegalArgumentException("No se encontró un ingrediente con el id " + id_ingrediente);
        }
        return ingredienteRepository.save(ingrediente);
    }
}
