package com.example.profit.Service;

import com.example.profit.Model.Objetivo;
import com.example.profit.Repository.ObjetivoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ObjetivoService {
    private final ObjetivoRepository objetivoRepository;

    public ObjetivoService(ObjetivoRepository objetivoRepository) {
        this.objetivoRepository = objetivoRepository;
    }

    public Objetivo guardar(Objetivo objetivo) {
        try {
            return objetivoRepository.save(objetivo);
        } catch (Exception e) {
            throw new RuntimeException("Error al guardar el objetivo " + e.getMessage(), e);
        }
    }

    public List<Objetivo> listar(){
        try {
            return objetivoRepository.findAll();
        } catch (Exception e) {
            throw new RuntimeException("Error al listar los objetivos " + e.getMessage(), e);
        }
    }

    public void eliminar(Long id_objetivo){
        try {
            if (!objetivoRepository.existsById(id_objetivo)) {
                throw new IllegalArgumentException("No se encontró un objetivo con el ID " + id_objetivo);
            }
            objetivoRepository.deleteById(id_objetivo);
        } catch (Exception e) {
            throw new RuntimeException("Error al eliminar el objetivo " + e.getMessage(), e);
        }
    }

    public Optional<Objetivo> listarPorId(long id_objetivo) {
        try {
            Optional<Objetivo> objetivo = objetivoRepository.findById(id_objetivo);
            if (objetivo.isEmpty()) {
                throw new IllegalArgumentException("Objetivo con ID " + id_objetivo + " no encontrado.");
            }
            return objetivo;
        } catch (Exception e) {
            throw new RuntimeException("Error al buscar objetivo por ID " + e.getMessage(), e);
        }
    }
}
