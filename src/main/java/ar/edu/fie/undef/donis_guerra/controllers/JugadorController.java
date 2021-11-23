package ar.edu.fie.undef.donis_guerra.controllers;

import ar.edu.fie.undef.donis_guerra.entities.Jugador;
import ar.edu.fie.undef.donis_guerra.representations.JugadorRepresentation;
import ar.edu.fie.undef.donis_guerra.requests.JugadorRequest;
import ar.edu.fie.undef.donis_guerra.services.JugadorService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class JugadorController {
    private final JugadorService jugadorService;

    public JugadorController(JugadorService jugadorService) {
        this.jugadorService = jugadorService;
    }

    @PostMapping("jugadores")
    private ResponseEntity<JugadorRepresentation> create(@RequestBody JugadorRequest jugador) {
        return ResponseEntity.ok(
                jugadorService.create(jugador).representation()
        );
    }

    @GetMapping("jugadores/{jugadorId}")
    private ResponseEntity<JugadorRepresentation> findById(@PathVariable Integer jugadorId) {
        return ResponseEntity.ok(
                jugadorService.findById(jugadorId).representation()
        );
    }

    @GetMapping("jugadores")
    private ResponseEntity<List<JugadorRepresentation>> findAllByActivo(@RequestParam boolean activo) {
        return ResponseEntity.ok(
                jugadorService.findByActivo(activo).
                        stream().map(Jugador::representation).collect(Collectors.toList())
        );
    }

    @GetMapping("turnos/{turnoId}/jugadores")
    private ResponseEntity<List<JugadorRepresentation>> findByTurnoId(@PathVariable Integer turnoId) {
        return ResponseEntity.ok(
                jugadorService.findByTurnoId(turnoId).stream()
                        .map(Jugador::representation).collect(Collectors.toList())
        );
    }

    @GetMapping("turnos/{turnoId}/jugadores/activos/")
    private ResponseEntity<List<JugadorRepresentation>> findActivoByTurnoId(@PathVariable Integer turnoId) {
        return ResponseEntity.ok(
                jugadorService.findActivoByTurnoId(turnoId).
                        stream().map(Jugador::representation).collect(Collectors.toList())
        );
    }

    @GetMapping("juegos/{juegoId}/jugadores")
    private ResponseEntity<List<JugadorRepresentation>> findByJuegoId(@PathVariable Integer juegoId) {
        return ResponseEntity.ok(
                jugadorService.findByJuegoId(juegoId).stream()
                        .map(Jugador::representation).collect(Collectors.toList())
        );
    }

    @GetMapping("juegos/{juegoId}/jugadores/activos")
    private ResponseEntity<List<JugadorRepresentation>> findActivoByJuegoId(@PathVariable Integer juegoId) {
        return ResponseEntity.ok(
                jugadorService.findActivobyJuegoId(juegoId).stream()
                        .map(Jugador::representation).collect(Collectors.toList())
        );
    }
}
