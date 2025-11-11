package com.example.pawdoptapi.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name = "adoptions") 
@AllArgsConstructor
@NoArgsConstructor
public class AdoptionRequest {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long adopterId;
    private Long ownerId;
    private Long petId;
    private String status = "Pendiente";
}