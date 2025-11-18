package com.example.pawdoptapi.repository;

import com.example.pawdoptapi.model.AdoptionRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface AdoptionRepository extends JpaRepository<AdoptionRequest, Long> {
    List<AdoptionRequest> findByAdopterId(Long id);
    List<AdoptionRequest> findByOwnerId(Long id);
    List<AdoptionRequest> findByPetId(Long petId);
}