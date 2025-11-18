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

    @GetMapping("/adopter/{id}")
    public List<AdoptionRequest> getByAdopter(@PathVariable Long id) {
        return service.findByAdopter(id);
    }

    @GetMapping("/owner/{id}")
    public List<AdoptionRequest> getByOwner(@PathVariable Long id) {
        return service.findByOwner(id);
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