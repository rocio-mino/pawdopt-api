package com.example.pawdoptapi.model;

import jakarta.persistence.*;
import lombok.*;

@Data
@Entity
@Table(name = "adoptions")
@NoArgsConstructor
@AllArgsConstructor
public class AdoptionRequest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "adopter_id")
    private User adopter;

    @ManyToOne
    @JoinColumn(name = "owner_id")
    private User owner;

    @ManyToOne
    @JoinColumn(name = "pet_id")
    private Pet pet;

    private String status = "Pendiente";
}