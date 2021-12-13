package ar.edu.fie.undef.donis_guerra.controllers;

import ar.edu.fie.undef.donis_guerra.entities.Turno;
import ar.edu.fie.undef.donis_guerra.representations.TurnoRepresentation;
import ar.edu.fie.undef.donis_guerra.services.TurnoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
public class TurnoController {
    private final TurnoService turnoService;

    public TurnoController(TurnoService turnoService) {
        this.turnoService = turnoService;
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

    // Pasar de turno
    @PostMapping("juegos/{juegoId}/turno")
    private ResponseEntity<TurnoRepresentation> pasarVariosTurnos(@PathVariable Integer juegoId,
                                                                  @RequestParam Optional<Integer> cantidad) {
        if (cantidad.isPresent()) {
            // lo hago aparte para saber el tamaño de la lista de turnos creados
            // puede ser menor que cantidad porque en el medio puede terminar el juego
            List<Turno> turnos = turnoService.pasarVariosTurnos(juegoId, cantidad.get());
            return ResponseEntity.ok(
                    turnos.get(turnos.size()-1).pasarVariosRepresentation(cantidad.get())
            );
        } else {
            return ResponseEntity.ok(
                    turnoService.pasarTurno(juegoId).representation()
            );
        }
    }
}
