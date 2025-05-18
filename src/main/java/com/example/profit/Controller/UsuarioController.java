package com.example.profit.Controller;

import com.example.profit.Model.Objetivo;
import com.example.profit.Model.Usuario;
import com.example.profit.Repository.ObjetivoRepository;
import com.example.profit.Repository.UsuarioRepository;
import com.example.profit.Service.UsuarioService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {

    private final UsuarioService usuarioService;
    private final UsuarioRepository usuarioRepository;
    private final ObjetivoRepository objetivoRepository;

    public UsuarioController(UsuarioService usuarioService, UsuarioRepository usuarioRepository, ObjetivoRepository objetivoRepository) {
        this.usuarioService = usuarioService;
        this.usuarioRepository = usuarioRepository;
        this.objetivoRepository = objetivoRepository;
    }

    @PostMapping("/guardar")
    public ResponseEntity<Usuario> guardarUsuario(@RequestBody Usuario usuario) {
        // Buscar y asignar objetivo
        Objetivo objetivo = objetivoRepository.findById(usuario.getId_objetivo())
                .orElseThrow(() -> new RuntimeException("Objetivo no encontrado con id: " + usuario.getId_objetivo()));
        usuario.setObjetivo(objetivo);

        // Guardar usuario
        return ResponseEntity.ok(usuarioService.guardar(usuario));
    }

    @GetMapping("/listar")
    public ResponseEntity<List<Usuario>> listarUsuario() {
        return ResponseEntity.ok(usuarioService.listar());
    }

    @GetMapping("/listar/{id_usuario}")
    public ResponseEntity<Usuario> obtenerUsuarioPorId(@PathVariable long id_usuario) {
        return usuarioService.listarPorId(id_usuario)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/eliminar/{id_usuario}")
    public ResponseEntity<Void> eliminarUsuario(@PathVariable long id_usuario) {
        usuarioService.eliminar(id_usuario);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/actualizar/{id_usuario}")
    public ResponseEntity<Usuario> actualizar(@PathVariable Long id_usuario, @RequestBody Usuario usuario) {
        // Buscar y asignar objetivo
        Objetivo objetivo = objetivoRepository.findById(usuario.getId_objetivo())
                .orElseThrow(() -> new RuntimeException("Objetivo no encontrado con id: " + usuario.getId_objetivo()));
        usuario.setObjetivo(objetivo);

        usuario.setId_usuario(id_usuario);
        Usuario usuarioActualizado = usuarioService.actualizar(id_usuario, usuario);
        return ResponseEntity.ok(usuarioActualizado);
    }
}

