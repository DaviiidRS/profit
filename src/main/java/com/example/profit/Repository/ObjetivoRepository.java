package com.example.profit.Repository;

import com.example.profit.Model.Categoria;
import com.example.profit.Model.Objetivo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ObjetivoRepository extends JpaRepository<Objetivo, Long> {
}
