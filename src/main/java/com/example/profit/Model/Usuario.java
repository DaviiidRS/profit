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
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_usuario;

    private String usuario;
    private String correo_electronico;
    private String contrasena;

    @ManyToOne
    @JoinColumn(name = "id_objetivo", nullable = false)
    @JsonIgnore
    private Objetivo objetivo;

    @Transient
    private Long id_objetivo;

    private Long caloria_diarias;

    @PostLoad
    public void cargarIds() {
        if (objetivo != null) {
            this.id_objetivo = objetivo.getId_objetivo();
        }
    }
}