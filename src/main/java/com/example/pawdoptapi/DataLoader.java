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
    private AdoptionRepository adoptionRepository;

    @Override
    public void run(String... args) {
        try {
            System.out.println("Esperando que Hibernate inicialice...");
            Thread.sleep(5000);

            if (userRepository.count() > 0) {
                System.out.println("Base ya tiene datos, DataLoader ignorado.");
                return;
            }

            Faker faker = new Faker();
            Random rnd = new Random();

            // ----------------------------
            // 1) Usuarios
            // ----------------------------
            List<User> usuarios = new ArrayList<>();

            for (int i = 0; i < 5; i++) {
                User u = new User();
                u.setNombre(faker.name().fullName());
                u.setEmail(faker.internet().emailAddress());
                u.setPassword("123456");
                u.setFotoUri("https://picsum.photos/200?random=" + rnd.nextInt(9999));

                usuarios.add(userRepository.save(u));
            }

            System.out.println("Usuarios creados: " + usuarios.size());

            // ----------------------------
            // 2) Mascotas
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
                p.setUbicacion(faker.address().city());

                // Foto simple URL (no Image entity)
                p.setFotoUri("https://picsum.photos/400?random=" + rnd.nextInt(9999));

                // Owner: ID simple
                Long ownerId = usuarios.get(rnd.nextInt(usuarios.size())).getId();
                p.setOwnerId(ownerId);

                mascotas.add(petRepository.save(p));
            }

            System.out.println("Mascotas creadas: " + mascotas.size());

            // ----------------------------
            // 3) Solicitudes de adopción
            // ----------------------------
            String[] estados = {"Pendiente", "Aceptada", "Rechazada"};

            for (int i = 0; i < 5; i++) {
                AdoptionRequest req = new AdoptionRequest();

                Pet pet = mascotas.get(rnd.nextInt(mascotas.size()));
                Long adopterId = usuarios.get(rnd.nextInt(usuarios.size())).getId();
                Long ownerId = pet.getOwnerId();

                req.setPetId(pet.getId());
                req.setAdopterId(adopterId);
                req.setOwnerId(ownerId);
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