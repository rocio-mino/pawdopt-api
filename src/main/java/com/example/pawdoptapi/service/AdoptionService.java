package com.example.pawdoptapi.service;

import com.example.pawdoptapi.model.AdoptionRequest;
import com.example.pawdoptapi.model.Pet;
import com.example.pawdoptapi.repository.AdoptionRepository;
import com.example.pawdoptapi.repository.PetRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdoptionService {

    private final AdoptionRepository repository;
    private final PetRepository petRepository;

    public AdoptionService(AdoptionRepository repository, PetRepository petRepository) {
        this.repository = repository;
        this.petRepository = petRepository;
    }

    public List<AdoptionRequest> findAll() {
        return repository.findAll();
    }

    public List<AdoptionRequest> findByAdopter(Long id) {
        return repository.findByAdopterId(id);
    }

    public List<AdoptionRequest> findByOwner(Long id) {
        return repository.findByOwnerId(id);
    }

    public AdoptionRequest save(AdoptionRequest req) {
        return repository.save(req);
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }

    public AdoptionRequest accept(Long id) {
        AdoptionRequest req = repository.findById(id).orElse(null);
        if (req == null) return null;

        req.setStatus("Aceptada");
        repository.save(req);

        // eliminar mascota de la lista
        petRepository.deleteById(req.getPetId());

        return req;
    }

    public AdoptionRequest reject(Long id) {
        AdoptionRequest req = repository.findById(id).orElse(null);
        if (req == null) return null;

        req.setStatus("Rechazada");
        repository.save(req);

        // se elimina la solicitud completamente
        repository.deleteById(id);

        return req;
    }
}