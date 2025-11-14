package com.example.pawdoptapi.model;

import jakarta.persistence.*;
import lombok.*;

@Data
@Entity
@Table(name = "images")
@NoArgsConstructor
@AllArgsConstructor
public class Image {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String url;

    private String descripcion;
}