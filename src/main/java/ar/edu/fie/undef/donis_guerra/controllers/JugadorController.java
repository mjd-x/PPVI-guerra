package ar.edu.fie.undef.donis_guerra.controllers;

import ar.edu.fie.undef.donis_guerra.entities.Jugador;
import ar.edu.fie.undef.donis_guerra.representations.JugadorCartasRepresentation;
import ar.edu.fie.undef.donis_guerra.representations.JugadorRepresentation;
import ar.edu.fie.undef.donis_guerra.requests.JugadorRequest;
import ar.edu.fie.undef.donis_guerra.services.JugadorService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
public class JugadorController {
    private final JugadorService jugadorService;

    public JugadorController(JugadorService jugadorService) {
        this.jugadorService = jugadorService;
    }

    // Crear un jugador
    @PostMapping("jugadores")
    private ResponseEntity<JugadorRepresentation> create(@RequestBody JugadorRequest jugador) {
        return ResponseEntity.ok(
                jugadorService.create(jugador).representation()
        );
    }

    // Buscar un jugador
    @GetMapping("jugadores/{jugadorId}")
    private ResponseEntity<JugadorRepresentation> findById(@PathVariable Integer jugadorId) {
        return ResponseEntity.ok(
                jugadorService.findById(jugadorId).representation()
        );
    }

    // Filtrar jugadores activos/inactivos (todos)
    @GetMapping("jugadores")
    private ResponseEntity<List<JugadorRepresentation>> findAllByActivo(@RequestParam boolean activo) {
        return ResponseEntity.ok(
                jugadorService.findByActivo(activo).
                        stream().map(Jugador::representation).collect(Collectors.toList())
        );
    }

    // Buscar jugadores en un turno
    @GetMapping("turnos/{turnoId}/jugadores")
    private ResponseEntity<List<JugadorRepresentation>> findByTurnoId(@PathVariable Integer turnoId,
                                                                      @RequestParam Optional<Boolean> activo) {
        if (activo.isPresent()) {
            // mostrar jugadores activos
            return ResponseEntity.ok(
                    jugadorService.findActivoByTurnoId(turnoId, activo.get()).
                            stream().map(Jugador::representation).collect(Collectors.toList())
            );
        } else {
            // mostrar jugadores
            return ResponseEntity.ok(
                    jugadorService.findByTurnoId(turnoId).stream()
                            .map(Jugador::representation).collect(Collectors.toList())
            );
        }
    }

    // Buscar jugadores en un juego
    @GetMapping("juegos/{juegoId}/jugadores")
    private ResponseEntity<List<JugadorCartasRepresentation>> findByJuegoId(@PathVariable Integer juegoId,
                                                                            @RequestParam Optional<Boolean> activo) {
        if (activo.isPresent()) {
            return ResponseEntity.ok(
                    jugadorService.findActivobyJuegoId(juegoId, activo.get()).
                            stream().map(Jugador::cartasRepresentation).collect(Collectors.toList())
            );
        } else {
            return ResponseEntity.ok(
                    jugadorService.findByJuegoId(juegoId).stream()
                            .map(Jugador::cartasRepresentation).collect(Collectors.toList())
            );
        }
    }
}
