package com.example.profit.Controller;

import com.example.profit.Model.Categoria;
import com.example.profit.Repository.CategoriaRepository;
import com.example.profit.Service.CategoriaService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/categoria")
public class CategoriaController {
    private final CategoriaService categoriaService;
    private final CategoriaRepository categoriaRepository;

    public CategoriaController(CategoriaService categoriaService, CategoriaRepository categoriaRepository) {
        this.categoriaService = categoriaService;
        this.categoriaRepository = categoriaRepository;
    }

    @PostMapping("/guardar")
    public ResponseEntity<Categoria> guardarCategoria(@RequestBody Categoria categoria) {
        return ResponseEntity.ok(categoriaService.guardar(categoria));
    }

    @GetMapping("/listar")
    public ResponseEntity<List<Categoria>> listarCategoria() {
        return ResponseEntity.ok(categoriaService.listar());
    }

    @GetMapping("/listar/{id_categoria}")
    public ResponseEntity<Categoria> obtenerCategoriaPorId(@PathVariable long id_categoria) {
        return categoriaService.listarPorId(id_categoria)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/eliminar/{id_categoria}")
    public ResponseEntity<Void> eliminarCategoria(@PathVariable long id_categoria) {
        categoriaService.eliminar(id_categoria);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/actualizar/{id_categoria}")
    public ResponseEntity<Categoria> actualizar(@PathVariable Long id_categoria, @RequestBody Categoria categoria) {
        categoria.setId_categoria(id_categoria);
        Categoria categoriaActualizada = categoriaService.actualizar(id_categoria, categoria);
        return ResponseEntity.ok(categoriaActualizada);
    }
}
