package com.example.profit.Model;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Receta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_receta;
    private String receta;
    private String descripcion;
    private String instrucciones;
    private Long tiempoTotal;
    private Long calorias;
    private Long proteinas;
    private Long carbohidratos;
    private Long grasas;

    @ManyToOne
    @JoinColumn(name = "id_categoria")
    private Categoria categoria;

    @ManyToOne
    @JoinColumn(name = "id_objetivo")
    private Objetivo objetivo;

    @OneToMany(mappedBy = "receta", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    private List<RecetaIngrediente> recetaIngredientes;
}
