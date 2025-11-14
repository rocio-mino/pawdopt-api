package com.example.pawdoptapi.service;

import com.example.pawdoptapi.model.AdoptionRequest;
import com.example.pawdoptapi.repository.AdoptionRepository;
import com.example.pawdoptapi.repository.PetRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdoptionService {

    private final AdoptionRepository adoptionRepository;
    private final PetRepository petRepository;

    public AdoptionService(AdoptionRepository adoptionRepository, PetRepository petRepository) {
        this.adoptionRepository = adoptionRepository;
        this.petRepository = petRepository;
    }

    public List<AdoptionRequest> findAll() {
        return adoptionRepository.findAll();
    }

    public List<AdoptionRequest> findByAdopter(Long adopterId) {
        return adoptionRepository.findByAdopterId(adopterId);
    }

    public List<AdoptionRequest> findByOwner(Long ownerId) {
        return adoptionRepository.findByOwnerId(ownerId);
    }

    public List<AdoptionRequest> findByPet(Long petId) {
        return adoptionRepository.findByPetId(petId);
    }

    public AdoptionRequest save(AdoptionRequest req) {
        return adoptionRepository.save(req);
    }

    public AdoptionRequest accept(Long id) {
        AdoptionRequest req = adoptionRepository.findById(id).orElse(null);
        if (req == null) return null;

        req.setStatus("Aceptada");
        adoptionRepository.save(req);

        // eliminar mascota una vez adoptada
        petRepository.deleteById(req.getPetId());

        return req;
    }

    public void reject(Long id) {
        adoptionRepository.deleteById(id);
    }
}