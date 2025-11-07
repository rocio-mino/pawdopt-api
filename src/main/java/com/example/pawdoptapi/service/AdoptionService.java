package com.example.pawdoptapi.service;

import com.example.pawdoptapi.model.AdoptionRequest;
import com.example.pawdoptapi.repository.AdoptionRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class AdoptionService {
    private final AdoptionRepository repository;

    public AdoptionService(AdoptionRepository repository) {
        this.repository = repository;
    }

    public List<AdoptionRequest> findAll() { return repository.findAll(); }
    public AdoptionRequest save(AdoptionRequest req) { return repository.save(req); }
    public void delete(Long id) { repository.deleteById(id); }

    public List<AdoptionRequest> findByAdopter(Long id) { return repository.findByAdopterId(id); }
    public List<AdoptionRequest> findByOwner(Long id) { return repository.findByOwnerId(id); }
}