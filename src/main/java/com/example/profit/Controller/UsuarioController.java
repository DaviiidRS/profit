package com.example.profit.Controller;

import com.example.profit.Model.Usuario;
import com.example.profit.Service.UsuarioService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {
    private final UsuarioService usuarioService;

    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @PostMapping("/guardar")
    public ResponseEntity<Usuario> guardarUsuario(@RequestBody Usuario usuario) {
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
}
