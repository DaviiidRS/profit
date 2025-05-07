package com.example.profit.Controller;

import com.example.profit.Model.Objetivo;
import com.example.profit.Service.ObjetivoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/objetivo")
public class ObjetivoController {
    private final ObjetivoService objetivoService;

    public ObjetivoController(ObjetivoService objetivoService) {
        this.objetivoService = objetivoService;
    }

    @PostMapping("/guardar")
    public ResponseEntity<Objetivo> guardarObjetivo(@RequestBody Objetivo objetivo) {
        return ResponseEntity.ok(objetivoService.guardar(objetivo));
    }

    @GetMapping("/listar")
    public ResponseEntity<List<Objetivo>> listarObjetivo() {
        return ResponseEntity.ok(objetivoService.listar());
    }

    @GetMapping("/listar/{id_objetivo}")
    public ResponseEntity<Objetivo> obtenerObjetivoPorId(@PathVariable long id_objetivo) {
        return objetivoService.listarPorId(id_objetivo)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/eliminar/{id_objetivo}")
    public ResponseEntity<Void> eliminarObjetivo(@PathVariable long id_objetivo) {
        objetivoService.eliminar(id_objetivo);
        return ResponseEntity.noContent().build();
    }
    @PutMapping("/actualizar/{id_objetivo}")
    public ResponseEntity<Objetivo> actualizar(@PathVariable Long id_objetivo, @RequestBody Objetivo objetivo) {
        objetivo.setId_objetivo(id_objetivo);
        Objetivo objetivoActualizado = objetivoService.actualizar(id_objetivo, objetivo);
        return ResponseEntity.ok(objetivoActualizado);
    }
}
