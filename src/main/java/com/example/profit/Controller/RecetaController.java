package com.example.profit.Controller;

import com.example.profit.Model.Categoria;
import com.example.profit.Model.Objetivo;
import com.example.profit.Model.Receta;
import com.example.profit.Repository.CategoriaRepository;
import com.example.profit.Repository.ObjetivoRepository;
import com.example.profit.Service.CategoriaService;
import com.example.profit.Service.ObjetivoService;
import com.example.profit.Service.RecetaService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/receta")
public class RecetaController {
    private final RecetaService recetaService;
    private final ObjetivoRepository objetivoRepository;
    private final CategoriaRepository categoriaRepository;

    public RecetaController(RecetaService recetaService, ObjetivoRepository objetivoRepository, CategoriaRepository categoriaRepository) {
        this.recetaService = recetaService;
        this.objetivoRepository = objetivoRepository;
        this.categoriaRepository = categoriaRepository;
    }

    @PostMapping("/guardar")
    public ResponseEntity<Receta> guardarReceta(@RequestBody Receta receta) {
        // Buscar y asignar objetivo
        Objetivo objetivo = objetivoRepository.findById(receta.getId_objetivo())
                .orElseThrow(() -> new RuntimeException("Objetivo no encontrado con id: " + receta.getId_objetivo()));
        receta.setObjetivo(objetivo);

        // Buscar y asignar categoria
        Categoria categoria = categoriaRepository.findById(receta.getId_categoria())
                .orElseThrow(() -> new RuntimeException("categoria no encontrada con id: " + receta.getId_categoria()));
        receta.setCategoria(categoria);

        // Guardar Receta
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
