package com.example.pawdoptapi.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.*;
import lombok.*;

@Data
@Entity
@Table(name = "pets")
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Pet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;
    private String especie;
    private int edad;
    private String raza;
    private String descripcion;
    private String fotoUri;
    private String ubicacion;
    private Long ownerId;
}