package com.example.evaluacion.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import com.example.evaluacion.model.Evaluacion;

public interface EvaluacionRepository extends JpaRepository<Evaluacion, Long> {}
