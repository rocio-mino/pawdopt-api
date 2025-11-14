package com.example.pawdoptapi;

import com.example.pawdoptapi.model.*;
import com.example.pawdoptapi.repository.*;
import net.datafaker.Faker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Component
public class DataLoader implements CommandLineRunner {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PetRepository petRepository;

    @Autowired
    private ImageRepository imageRepository;

    @Autowired
    private AdoptionRepository adoptionRepository;

    @Override
    public void run(String... args) {
        try {
            System.out.println("Esperando que Hibernate inicialice...");
            Thread.sleep(9000);

            if (userRepository.count() > 0) {
                System.out.println("Base ya tiene datos, DataLoader ignorado.");
                return;
            }

            Faker faker = new Faker();
            Random rnd = new Random();

            // ----------------------------
            // 1) USUARIOS
            // ----------------------------
            List<User> usuarios = new ArrayList<>();

            for (int i = 0; i < 5; i++) {
                User u = new User();

                u.setNombre(faker.name().fullName());
                u.setEmail(faker.internet().emailAddress());
                u.setPassword("123456");

                // Crear imagen de perfil
                Image img = new Image();
                img.setUrl("https://picsum.photos/300?random=" + rnd.nextInt(9999));
                img.setDescripcion("Imagen de perfil");
                imageRepository.save(img);

                u.setFotoPerfil(img);

                usuarios.add(userRepository.save(u));
            }

            System.out.println("Usuarios creados: " + usuarios.size());

            // ----------------------------
            // 2) MASCOTAS
            // ----------------------------
            List<Pet> mascotas = new ArrayList<>();
            String[] especies = {"Perro", "Gato"};

            for (int i = 0; i < 10; i++) {
                Pet p = new Pet();
                p.setNombre(faker.animal().name());
                p.setEspecie(especies[rnd.nextInt(especies.length)]);
                p.setEdad(rnd.nextInt(1, 15));
                p.setRaza(faker.dog().breed());
                p.setDescripcion(faker.lorem().sentence());
                p.setUbicacion(faker.address().cityName());

                // Imagen principal
                Image img = new Image();
                img.setUrl("https://picsum.photos/400?random=" + rnd.nextInt(9999));
                img.setDescripcion("Foto mascota");
                imageRepository.save(img);

                p.setFotoPrincipal(img);

                // Dueño random
                p.setOwner(usuarios.get(rnd.nextInt(usuarios.size())));

                mascotas.add(petRepository.save(p));
            }

            System.out.println("Mascotas creadas: " + mascotas.size());

            // ----------------------------
            // 3) SOLICITUDES DE ADOPCIÓN
            // ----------------------------
            for (int i = 0; i < 5; i++) {
                AdoptionRequest req = new AdoptionRequest();

                User adopter = usuarios.get(rnd.nextInt(usuarios.size()));
                Pet pet = mascotas.get(rnd.nextInt(mascotas.size()));
                User owner = pet.getOwner();

                req.setAdopter(adopter);
                req.setOwner(owner);
                req.setPet(pet);

                String[] estados = {"Pendiente", "Aceptada", "Rechazada"};
                req.setStatus(estados[rnd.nextInt(estados.length)]);

                adoptionRepository.save(req);
            }

            System.out.println("Solicitudes de adopción creadas.");

            System.out.println("DataLoader completado con éxito.");

        } catch (Exception e) {
            System.err.println("Error en DataLoader: " + e.getMessage());
            e.printStackTrace();
        }
    }
}