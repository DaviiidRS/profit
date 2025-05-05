package com.example.profit.Repository;

import com.example.profit.Model.Categoria;
import com.example.profit.Model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
}
