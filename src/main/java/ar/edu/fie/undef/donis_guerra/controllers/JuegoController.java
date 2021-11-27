package ar.edu.fie.undef.donis_guerra.controllers;

import ar.edu.fie.undef.donis_guerra.entities.Juego;
import ar.edu.fie.undef.donis_guerra.representations.JuegoRepresentation;
import ar.edu.fie.undef.donis_guerra.representations.JuegoMessageRepresentation;
import ar.edu.fie.undef.donis_guerra.requests.JuegoRequest;
import ar.edu.fie.undef.donis_guerra.services.JuegoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class JuegoController {
    private final JuegoService juegoService;

    public JuegoController(JuegoService juegoService) {
        this.juegoService = juegoService;
    }

    // Crear un juego nuevo
    @PostMapping("juegos")
    private ResponseEntity<JuegoRepresentation> create (@RequestBody JuegoRequest juego){
        return ResponseEntity.ok(
                juegoService.create(juego).representation()
        );
    }

    // Buscar un juego
    @GetMapping("juegos/{juegoId}")
    private ResponseEntity<JuegoRepresentation> findById(@PathVariable Integer juegoId) {
        return ResponseEntity.ok(
                juegoService.findById(juegoId).representation()
        );
    }

    // Buscar los juegos terminados
    @GetMapping("juegos/terminados")
    private ResponseEntity<List<JuegoRepresentation>> findAllByTerminado() {
        return ResponseEntity.ok(
                juegoService.findAllByTerminado().stream()
                        .map(Juego::representation).collect(Collectors.toList())
        );
    }

    // Iniciar un juego que ya existe
    @PostMapping("juegos/{juegoId}/iniciar")
    private ResponseEntity<JuegoMessageRepresentation> iniciarJuego(@PathVariable Integer juegoId) {
        return ResponseEntity.ok(
                juegoService.iniciarJuego(juegoId).iniciadoRepresentation()
        );
    }

    // Pasar de turno
    @PostMapping("juegos/{juegoId}/pasarTurno")
    private ResponseEntity<JuegoMessageRepresentation> pasarTurno(@PathVariable Integer juegoId) {
        return ResponseEntity.ok(
                juegoService.pasarTurno(juegoId).pasarTurnoRepresentation()
        );
    }

    // Pasar de turno
    @PostMapping("juegos/{juegoId}/pasarTurno/{cantidad}")
    private ResponseEntity<JuegoMessageRepresentation> pasarVariosTurnos(@PathVariable Integer juegoId,
                                                                         @PathVariable Integer cantidad) {
        return ResponseEntity.ok(
                juegoService.pasarVariosTurnos(juegoId, cantidad).pasarVariosTurnosRepresentation(cantidad)
        );
    }
}
