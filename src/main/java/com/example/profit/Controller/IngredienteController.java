package com.example.profit.Controller;

import com.example.profit.Model.Ingrediente;
import com.example.profit.Service.IngredienteService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ingrediente")
public class IngredienteController {
    private final IngredienteService ingredienteService;

    public IngredienteController(IngredienteService ingredienteService) {
        this.ingredienteService = ingredienteService;
    }

    @PostMapping("/guardar")
    public ResponseEntity<Ingrediente> guardarIngrediente(@RequestBody Ingrediente ingrediente) {
        return ResponseEntity.ok(ingredienteService.guardar(ingrediente));
    }

    @GetMapping("/listar")
    public ResponseEntity<List<Ingrediente>> listarIngrediente() {
        return ResponseEntity.ok(ingredienteService.listar());
    }

    @GetMapping("/listar/{id_ingrediente}")
    public ResponseEntity<Ingrediente> obtenerIngredientePorId(@PathVariable long id_ingrediente) {
        return ingredienteService.listarPorId(id_ingrediente)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/eliminar/{id_ingrediente}")
    public ResponseEntity<Void> eliminarIngrediente(@PathVariable long id_ingrediente) {
        ingredienteService.eliminar(id_ingrediente);
        return ResponseEntity.noContent().build();
    }
}

