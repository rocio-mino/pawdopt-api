package com.example.pawdoptapi.service;

import com.example.pawdoptapi.model.User;
import com.example.pawdoptapi.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    // Obtener usuario por ID
    public User findById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
    }

    // Guardar / actualizar usuario
    public User save(User user) {
        return userRepository.save(user);
    }

    // Eliminar usuario
    public void delete(Long id) {
        userRepository.deleteById(id);
    }

    // Login
    public User login(String email, String password) {
        return userRepository.findByEmailAndPassword(email, password);
    }

    public List<User> findAll() {
        return userRepository.findAll();
    }
}