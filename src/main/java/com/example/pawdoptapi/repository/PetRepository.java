package com.example.pawdoptapi.repository;

import com.example.pawdoptapi.model.Pet;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PetRepository extends JpaRepository<Pet, Long> { }