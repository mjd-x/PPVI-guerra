package ar.edu.fie.undef.donis_guerra.controllers;

import ar.edu.fie.undef.donis_guerra.representations.TurnoRepresentation;
import ar.edu.fie.undef.donis_guerra.requests.TurnoRequest;
import ar.edu.fie.undef.donis_guerra.services.TurnoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class TurnoController {
    private final TurnoService turnoService;

    public TurnoController(TurnoService turnoService) {
        this.turnoService = turnoService;
    }

    @PostMapping("turnos")
    private ResponseEntity<TurnoRepresentation> create (@RequestBody TurnoRequest turno){
        return ResponseEntity.ok(
                turnoService.create(turno).representation()
        );
    }

    @GetMapping("turnos/{turnoId}")
    private ResponseEntity<TurnoRepresentation> findById(@PathVariable Integer turnoId) {
        return ResponseEntity.ok(
                turnoService.findById(turnoId).representation()
        );
    }
}
