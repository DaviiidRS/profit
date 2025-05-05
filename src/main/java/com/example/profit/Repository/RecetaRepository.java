package com.example.profit.Repository;

import com.example.profit.Model.Categoria;
import com.example.profit.Model.Receta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RecetaRepository extends JpaRepository<Receta, Long> {
}
