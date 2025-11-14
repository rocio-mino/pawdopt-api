package com.example.pawdoptapi.repository;

import com.example.pawdoptapi.model.AdoptionRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface AdoptionRepository extends JpaRepository<AdoptionRequest, Long> {

    // Buscar todas las solicitudes hechas por X usuario
    List<AdoptionRequest> findByAdopterId(Long adopterId);

    // Buscar todas las solicitudes recibidas por X usuario
    List<AdoptionRequest> findByOwnerId(Long ownerId);

    // Mascota adoptada/rechazada
    List<AdoptionRequest> findByPetId(Long petId);
}
