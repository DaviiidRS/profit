package com.example.profit.Service;

import com.example.profit.Model.Objetivo;
import com.example.profit.Model.Usuario;
import com.example.profit.Repository.ObjetivoRepository;
import com.example.profit.Repository.UsuarioRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService {
    private final UsuarioRepository usuarioRepository;
    private final ObjetivoRepository objetivoRepository;

    public UsuarioService(UsuarioRepository usuarioRepository, ObjetivoRepository objetivoRepository) {
        this.usuarioRepository = usuarioRepository;
        this.objetivoRepository = objetivoRepository;
    }

    public Usuario guardar(Usuario usuario) {
        try {
            return usuarioRepository.save(usuario);
        } catch (Exception e) {
            throw new RuntimeException("Error al guardar el usuario " + e.getMessage(), e);
        }
    }

    public List<Usuario> listar(){
        try {
            return usuarioRepository.findAll();
        } catch (Exception e) {
            throw new RuntimeException("Error al listar los usuarios " + e.getMessage(), e);
        }
    }

    public void eliminar(Long id_usuario){
        try {
            if (!usuarioRepository.existsById(id_usuario)) {
                throw new IllegalArgumentException("No se encontró un usuario con el ID " + id_usuario);
            }
            usuarioRepository.deleteById(id_usuario);
        } catch (Exception e) {
            throw new RuntimeException("Error al eliminar el usuario " + e.getMessage(), e);
        }
    }

    public Optional<Usuario> listarPorId(long id_usuario) {
        try {
            Optional<Usuario> usuario = usuarioRepository.findById(id_usuario);
            if (usuario.isEmpty()) {
                throw new IllegalArgumentException("Usuario con ID " + id_usuario + " no encontrado.");
            }
            return usuario;
        } catch (Exception e) {
            throw new RuntimeException("Error al buscar usuario por ID " + e.getMessage(), e);
        }
    }

    public Usuario actualizar(Long id_usuario, Usuario usuario) {
        if (!usuarioRepository.existsById(id_usuario)) {
            throw new IllegalArgumentException("No se encontró un usuario con el id " + id_usuario);
        }
        if (usuario.getId_objetivo() != null) {
            Objetivo objetivo = objetivoRepository.findById(usuario.getId_objetivo())
                    .orElseThrow(() -> new IllegalArgumentException("No se encontró un objetivo con el id " + usuario.getId_objetivo()));
            usuario.setObjetivo(objetivo);
        }
        return usuarioRepository.save(usuario);
    }
}
