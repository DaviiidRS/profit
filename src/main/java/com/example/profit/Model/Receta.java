package com.example.profit.Model;
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
    private Long categoria_id;
    private String objetivoFitness;

    @OneToMany(mappedBy = "receta")
    private List<RecetaIngrediente> recetaIngredientes;
}
