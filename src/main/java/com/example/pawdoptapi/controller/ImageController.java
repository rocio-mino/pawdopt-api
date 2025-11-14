package com.example.pawdoptapi.controller;

import com.example.pawdoptapi.model.Image;
import com.example.pawdoptapi.service.ImageService;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/images")
@CrossOrigin(origins = "*")
public class ImageController {

    private final ImageService service;

    public ImageController(ImageService service) {
        this.service = service;
    }

    @PostMapping("/upload")
    public Image uploadImage(@RequestParam("file") MultipartFile file) throws Exception {

        // Si no usas un proveedor externo, simulamos una URL
        String fakeUrl = "https://pawdopt-bucket.fake/" + file.getOriginalFilename();

        Image img = new Image();
        img.setUrl(fakeUrl);
        img.setDescripcion("Imagen subida");

        return service.save(img);
    }

    @GetMapping("/{id}")
    public Image getImage(@PathVariable Long id) {
        return service.findById(id);
    }

    @DeleteMapping("/{id}")
    public void deleteImage(@PathVariable Long id) {
        service.delete(id);
    }
}