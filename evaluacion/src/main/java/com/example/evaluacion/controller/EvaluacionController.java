package com.example.evaluacion.controller;

import com.example.evaluacion.model.EstudianteDTO;
import com.example.evaluacion.model.Evaluacion;
import com.example.evaluacion.repository.EvaluacionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/evaluaciones")
public class EvaluacionController {

    @Autowired
    private EvaluacionRepository repo;

    private final RestTemplate restTemplate = new RestTemplate();
    private final String ESTUDIANTE_URL = "http://estudiante-service:8080/estudiantes/";

    @PostMapping
    public ResponseEntity<?> crearEvaluacion(@RequestBody Evaluacion evaluacion) {
        String rut = evaluacion.getRutEstudiante();

        // Llamada al microservicio de estudiante para verificar si existe el RUT
        try {
                ResponseEntity<EstudianteDTO> response = restTemplate.getForEntity(ESTUDIANTE_URL + rut, EstudianteDTO.class);
            if (response.getStatusCode() != HttpStatus.OK || response.getBody() == null) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                        .body("El estudiante con RUT " + rut + " no existe.");
            }
        } catch (HttpClientErrorException.NotFound e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("El estudiante con RUT " + rut + " no existe.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error al verificar el estudiante: " + e.getMessage());
        }

        Evaluacion evaluacionGuardada = repo.save(evaluacion);
        return ResponseEntity.status(HttpStatus.CREATED).body(evaluacionGuardada);
    }

    @GetMapping
    public List<Evaluacion> findAll() {
        return repo.findAll();
    }

    @GetMapping("/{id}")
    public Optional<Evaluacion> findById(@PathVariable Long id) {
        return repo.findById(id);
    }
}
