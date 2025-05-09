package com.example.profit.Controller;

import com.example.profit.Model.Receta;
import com.example.profit.Service.RecetaService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/receta")
public class RecetaController {
    private final RecetaService recetaService;

    public RecetaController(RecetaService recetaService) {
        this.recetaService = recetaService;
    }

    @PostMapping("/guardar")
    public ResponseEntity<Receta> guardarReceta(@RequestBody Receta receta) {
        return ResponseEntity.ok(recetaService.guardar(receta));
    }

    @GetMapping("/listar")
    public ResponseEntity<List<Receta>> listarReceta() {
        return ResponseEntity.ok(recetaService.listar());
    }

    @GetMapping("/listar/{id_receta}")
    public ResponseEntity<Receta> obtenerRecetaPorId(@PathVariable long id_receta) {
        return recetaService.listarPorId(id_receta)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/eliminar/{id_receta}")
    public ResponseEntity<Void> eliminarReceta(@PathVariable long id_receta) {
        recetaService.eliminar(id_receta);
        return ResponseEntity.noContent().build();
    }
    @PutMapping("/actualizar/{id_receta}")
    public ResponseEntity<Receta> actualizar(@PathVariable Long id_receta, @RequestBody Receta receta) {
        receta.setId_receta(id_receta);
        Receta recetaActualizada = recetaService.actualizar(id_receta, receta);
        return ResponseEntity.ok(recetaActualizada);
    }

    @GetMapping("/filtrarPorObjetivo")
    public ResponseEntity<List<Receta>> filtrarPorObjetivo(@RequestParam Long id_objetivo) {
        return ResponseEntity.ok(recetaService.filtrarPorObjetivoId(id_objetivo));
    }
}
