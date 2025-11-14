package com.example.pawdoptapi.controller;

import com.example.pawdoptapi.model.AdoptionRequest;
import com.example.pawdoptapi.service.AdoptionService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/adoptions")
@CrossOrigin(origins = "*")
public class AdoptionController {

    private final AdoptionService service;

    public AdoptionController(AdoptionService service) {
        this.service = service;
    }

    @GetMapping
    public List<AdoptionRequest> getAll() {
        return service.findAll();
    }

    // NUEVO: solicitudes enviadas por un usuario
    @GetMapping("/adopter/{id}")
    public List<AdoptionRequest> getByAdopter(@PathVariable Long id) {
        return service.findByAdopter(id);
    }

    // NUEVO: solicitudes recibidas por un dueño
    @GetMapping("/owner/{id}")
    public List<AdoptionRequest> getByOwner(@PathVariable Long id) {
        return service.findByOwner(id);
    }

    // NUEVO: solicitudes relacionadas a una mascota
    @GetMapping("/pet/{id}")
    public List<AdoptionRequest> getByPet(@PathVariable Long id) {
        return service.findByPet(id);
    }

    @PostMapping
    public AdoptionRequest create(@RequestBody AdoptionRequest req) {
        return service.save(req);
    }

    @PatchMapping("/{id}/accept")
    public AdoptionRequest accept(@PathVariable Long id) {
        return service.accept(id);
    }

    @PatchMapping("/{id}/reject")
    public void reject(@PathVariable Long id) {
        service.reject(id);
    }
}