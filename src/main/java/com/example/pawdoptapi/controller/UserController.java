package com.example.pawdoptapi.controller;

import com.example.pawdoptapi.model.User;
import com.example.pawdoptapi.service.UserService;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/users")
@CrossOrigin(origins = "*")
public class UserController {
    private final UserService service;

    public UserController(UserService service) {
        this.service = service;
    }

    @GetMapping
    public List<User> getAllUsers() { return service.findAll(); }

    @GetMapping("/{id}")
    public User getUserById(@PathVariable Long id) { return service.findById(id); }

    @PostMapping
    public User createUser(@RequestBody User user) { return service.save(user); }

    @PutMapping("/{id}")
    public User updateUser(@PathVariable Long id, @RequestBody User user) {
        user.setId(id);
        return service.save(user);
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable Long id) { service.delete(id); }

    @PostMapping("/login")
    public User login(@RequestParam String email, @RequestParam String password) {
        return service.login(email, password);
    }
}