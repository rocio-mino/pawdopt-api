package com.example.pawdoptapi.service;

import com.example.pawdoptapi.model.Image;
import com.example.pawdoptapi.repository.ImageRepository;
import org.springframework.stereotype.Service;

@Service
public class ImageService {

    private final ImageRepository repository;

    public ImageService(ImageRepository repository) {
        this.repository = repository;
    }

    public Image save(Image image) {
        return repository.save(image);
    }

    public Image findById(Long id) {
        return repository.findById(id).orElse(null);
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }
}