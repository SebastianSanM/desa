package com.example.estudiante.repository;



import org.springframework.data.jpa.repository.JpaRepository;

import com.example.estudiante.model.Estudiante;

public interface EstudianteRepository extends JpaRepository<Estudiante, String> {}

