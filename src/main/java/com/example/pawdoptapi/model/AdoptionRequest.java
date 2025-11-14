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

    private Long adopterId;
    private Long ownerId;
    private Long petId;

    private String status = "Pendiente";
}