package com.example.pawdoptapi.service;

import com.example.pawdoptapi.model.Pet;
import com.example.pawdoptapi.repository.PetRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class PetService {
    private final PetRepository repository;

    public PetService(PetRepository repository) {
        this.repository = repository;
    }

    public List<Pet> findAll() { return repository.findAll(); }
    public Pet findById(Long id) { return repository.findById(id).orElse(null); }
    public Pet save(Pet pet) { return repository.save(pet); }
    public void delete(Long id) { repository.deleteById(id); }
}