package com.example.pawdoptapi.model;

import jakarta.persistence.*;
import lombok.*;

@Data
@Entity
@Table(name = "pets")
@NoArgsConstructor
@AllArgsConstructor
public class Pet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;
    private String especie;
    private int edad;
    private String raza;
    private String descripcion;

    // URL o content://
    @Column(nullable = true)
    private String fotoUri = null;

    private String ubicacion;

    // Solo el ID del usuario dueño
    private Long ownerId;
}