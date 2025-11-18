package com.example.pawdoptapi.controller;

import com.example.pawdoptapi.model.User;
import com.example.pawdoptapi.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/users")
@CrossOrigin(origins = "*")
public class UserController {

    private final UserService service;

    public UserController(UserService service) {
        this.service = service;
    }

    @GetMapping
    public List<User> getAll() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public User getById(@PathVariable Long id) {
        return service.findById(id);
    }

    @PostMapping
    public User create(@RequestBody User user) {
        return service.save(user);
    }

    @PutMapping("/{id}")
    public User update(@PathVariable Long id, @RequestBody User user) {
        user.setId(id);
        return service.save(user);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }

    @PostMapping("/login")
    public User login(@RequestBody Map<String, String> loginData) {
        String email = loginData.get("email");
        String password = loginData.get("password");
        return service.login(email, password);
    }

    @PatchMapping("/{id}/photo")
    public ResponseEntity<User> updatePhoto(
            @PathVariable Long id,
            @RequestBody Map<String, String> body
    ) {
        String fotoUri = body.get("fotoUri");

        User user = service.findById(id);
        user.setFotoUri(fotoUri);

        User updated = service.save(user);
        return ResponseEntity.ok(updated);
    }
}