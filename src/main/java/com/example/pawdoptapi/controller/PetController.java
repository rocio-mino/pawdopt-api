package com.example.pawdoptapi.controller;

import com.example.pawdoptapi.model.Pet;
import com.example.pawdoptapi.service.PetService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/pets")
@CrossOrigin(origins = "*")
public class PetController {

    private final PetService service;

    public PetController(PetService service) {
        this.service = service;
    }

    @GetMapping
    public List<Pet> getAll() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public Pet getById(@PathVariable Long id) {
        return service.findById(id);
    }

    @PostMapping
    public Pet create(@RequestBody Pet pet) {
        return service.save(pet);
    }

    @PutMapping("/{id}")
    public Pet update(@PathVariable Long id, @RequestBody Pet pet) {
        pet.setId(id);
        return service.save(pet);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
}