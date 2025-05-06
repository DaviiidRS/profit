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
    @JsonIgnore
    private Long id_usuario;

    private String usuario;
    private String correo_electronico;
    private String contrasena;

    @ManyToOne
    @JoinColumn(name = "id_objetivo", nullable = false)
    private Objetivo objetivo;

    private Long caloria_diarias;
}
