package com.example.pawdoptapi.model;

import jakarta.persistence.*;
import lombok.*;
import java.util.List;

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
    private String ubicacion;

    @OneToOne
    @JoinColumn(name = "foto_principal_id")
    private Image fotoPrincipal;

    @ManyToOne
    @JoinColumn(name = "owner_id")
    private User owner;

    @OneToMany
    @JoinColumn(name = "pet_id")
    private List<Image> galeria;
}