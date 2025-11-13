package com.example.pawdoptapi.repository;

import com.example.pawdoptapi.model.Pet;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PetRepository extends JpaRepository<Pet, Long> {
    List<Pet> findByEspecieIgnoreCase(String especie);
}