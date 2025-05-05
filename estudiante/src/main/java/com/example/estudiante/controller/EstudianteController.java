package com.example.estudiante.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.example.estudiante.model.Estudiante;
import com.example.estudiante.repository.EstudianteRepository;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/estudiantes")
public class EstudianteController {

    @Autowired
    private EstudianteRepository repo;

    @PostMapping
    public Estudiante create(@RequestBody Estudiante e) {
        return repo.save(e);
    }

    @GetMapping
    public List<Estudiante> findAll() {
        return repo.findAll();
    }

    @GetMapping("/{rut}")
    public Optional<Estudiante> findById(@PathVariable String rut) {
        return repo.findById(rut);
    }
}
