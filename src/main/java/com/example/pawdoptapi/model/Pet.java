package com.example.pawdoptapi.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "pets") 
public class Pet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;
    private String especie;
    private int edad;
    private String raza;
    private String descripcion;
    private String ubicacion;
    private String fotoUri;
    private Long ownerId;
}