package com.example.profit.Controller;

import com.example.profit.Model.RecetaIngrediente;
import com.example.profit.Service.RecetaIngredienteService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/recetaIngrediente")
public class RecetaIngredienteController {
    private final RecetaIngredienteService recetaIngredienteService;

    public RecetaIngredienteController(RecetaIngredienteService recetaIngredienteService) {
        this.recetaIngredienteService = recetaIngredienteService;
    }

    @PostMapping("/guardar")
    public ResponseEntity<RecetaIngrediente> guardarRecetaIngrediente(@RequestBody RecetaIngrediente recetaIngrediente) {
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
