package com.example.profit.Repository;

import com.example.profit.Model.Receta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RecetaRepository extends JpaRepository<Receta, Long> {
    @Query(value = "SELECT r.* FROM receta r " +
            "JOIN objetivo o ON r.id_objetivo = o.id_objetivo " +
            "WHERE o.id_objetivo = :idObjetivo", nativeQuery = true)
    List<Receta> findByObjetivoId(@Param("idObjetivo") Long idObjetivo);
}
