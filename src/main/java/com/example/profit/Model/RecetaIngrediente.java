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
    private Long id_recetaIngrediente;

    @ManyToOne
    @JoinColumn(name = "id_receta")
    @JsonIgnore
    private Receta receta;

    @Transient
    private Long id_receta;

    @ManyToOne
    @JoinColumn(name = "id_ingrediente")
    @JsonIgnore
    private Ingrediente ingrediente;

    @Transient
    private Long id_ingrediente;

    private Long cantidad;

    @PostLoad
    public void asignarIds() {
        if (receta != null) {
            this.id_receta = receta.getId_receta();
        }
        if (ingrediente != null) {
            this.id_ingrediente = ingrediente.getId_ingrediente();
        }
    }
}
