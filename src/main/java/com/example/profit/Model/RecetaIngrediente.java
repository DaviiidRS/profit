package com.example.profit.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RecetaIngrediente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonIgnore
    private Long id_recetaIngrediente;

    @ManyToOne
    @JoinColumn(name = "id_receta")
    @JsonIgnore
    private Receta receta;

    @ManyToOne
    @JoinColumn(name = "id_ingrediente")
    @JsonIgnore
    private Ingrediente ingrediente;

    private Long cantidad;
}
