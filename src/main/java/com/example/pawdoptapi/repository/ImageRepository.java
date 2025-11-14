package com.example.pawdoptapi.repository;

import com.example.pawdoptapi.model.Image;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ImageRepository extends JpaRepository<Image, Long> { }