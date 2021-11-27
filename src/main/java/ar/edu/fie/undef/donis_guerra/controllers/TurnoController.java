package ar.edu.fie.undef.donis_guerra.controllers;

import ar.edu.fie.undef.donis_guerra.entities.Turno;
import ar.edu.fie.undef.donis_guerra.representations.TurnoRepresentation;
import ar.edu.fie.undef.donis_guerra.requests.TurnoRequest;
import ar.edu.fie.undef.donis_guerra.services.TurnoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
public class TurnoController {
    private final TurnoService turnoService;

    public TurnoController(TurnoService turnoService) {
        this.turnoService = turnoService;
    }

    // Crear un turno
    @PostMapping("turnos")
    private ResponseEntity<TurnoRepresentation> create (@RequestBody TurnoRequest turno){
        return ResponseEntity.ok(
                turnoService.create(turno).representation()
        );
    }

    // Buscar un turno
    @GetMapping("turnos/{turnoId}")
    private ResponseEntity<TurnoRepresentation> findById(@PathVariable Integer turnoId) {
        return ResponseEntity.ok(
                turnoService.findById(turnoId).representation()
        );
    }

    // Buscar turnos en un juego
    @GetMapping("juegos/{juegoId}/turnos")
    private ResponseEntity<List<TurnoRepresentation>> findByJuegoId(@PathVariable Integer juegoId) {
        return ResponseEntity.ok(
                turnoService.findByJuegoId(juegoId).stream()
                        .map(Turno::representation).collect(Collectors.toList())
        );
    }
}
