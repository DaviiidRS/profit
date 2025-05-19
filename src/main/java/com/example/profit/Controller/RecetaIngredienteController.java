package com.example.profit.Controller;

import com.example.profit.Model.*;
import com.example.profit.Repository.IngredienteRepository;
import com.example.profit.Repository.RecetaRepository;
import com.example.profit.Service.RecetaIngredienteService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/recetaIngrediente")
public class RecetaIngredienteController {
    private final RecetaIngredienteService recetaIngredienteService;
    private final RecetaRepository recetaRepository;
    private final IngredienteRepository ingredienteRepository;

    public RecetaIngredienteController(RecetaIngredienteService recetaIngredienteService, RecetaRepository recetaRepository, IngredienteRepository ingredienteRepository) {
        this.recetaIngredienteService = recetaIngredienteService;
        this.recetaRepository = recetaRepository;
        this.ingredienteRepository = ingredienteRepository;
    }

    @PostMapping("/guardar")
    public ResponseEntity<RecetaIngrediente> guardarRecetaIngrediente(@RequestBody RecetaIngrediente recetaIngrediente) {
        // Buscar y asignar receta
        Receta receta = recetaRepository.findById(recetaIngrediente.getId_receta())
                .orElseThrow(() -> new RuntimeException("Receta no encontrada con id: " + recetaIngrediente.getId_receta()));
        recetaIngrediente.setReceta(receta);

        // Buscar y asignar ingrediente
        Ingrediente ingrediente = ingredienteRepository.findById(recetaIngrediente.getId_ingrediente())
                .orElseThrow(() -> new RuntimeException("Ingrediente no encontrado con id: " + recetaIngrediente.getId_ingrediente()));
        recetaIngrediente.setIngrediente(ingrediente);

        return ResponseEntity.ok(recetaIngredienteService.guardar(recetaIngrediente));
    }

    @GetMapping("/listar")
    public ResponseEntity<List<RecetaIngrediente>> listarRecetaIngrediente() {
        return ResponseEntity.ok(recetaIngredienteService.listar());
    }

    @GetMapping("/listar/{id_recetaIngrediente}")
    public ResponseEntity<RecetaIngrediente> obtenerRecetaIngredientePorId(@PathVariable long id_recetaIngrediente) {
        return recetaIngredienteService.listarPorId(id_recetaIngrediente)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/eliminar/{id_recetaIngrediente}")
    public ResponseEntity<Void> eliminarRecetaIngrediente(@PathVariable long id_recetaIngrediente) {
        recetaIngredienteService.eliminar(id_recetaIngrediente);
        return ResponseEntity.noContent().build();
    }
    @PutMapping("/actualizar/{id_recetaIngrediente}")
    public ResponseEntity<RecetaIngrediente> actualizar(@PathVariable Long id_recetaIngrediente, @RequestBody RecetaIngrediente recetaIngrediente) {
        recetaIngrediente.setId_recetaIngrediente(id_recetaIngrediente);
        RecetaIngrediente recetaIngredienteActualizada = recetaIngredienteService.actualizar(id_recetaIngrediente, recetaIngrediente);
        return ResponseEntity.ok(recetaIngredienteActualizada);
    }
}
