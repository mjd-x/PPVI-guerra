package ar.edu.fie.undef.donis_guerra.controllers;

import ar.edu.fie.undef.donis_guerra.entities.Juego;
import ar.edu.fie.undef.donis_guerra.representations.JuegoIniciadoRepresentation;
import ar.edu.fie.undef.donis_guerra.representations.JuegoRepresentation;
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

    @PostMapping("juegos")
    private ResponseEntity<JuegoRepresentation> create (@RequestBody JuegoRequest juego){
        return ResponseEntity.ok(
                juegoService.create(juego).representation()
        );
    }

    @GetMapping("juegos/{juegoId}")
    private ResponseEntity<JuegoRepresentation> findById(@PathVariable Integer juegoId) {
        return ResponseEntity.ok(
                juegoService.findById(juegoId).representation()
        );
    }

    @GetMapping("juegos/terminados")
    private ResponseEntity<List<JuegoRepresentation>> findAllByTerminado() {
        return ResponseEntity.ok(
                juegoService.findAllByTerminado().stream()
                        .map(Juego::representation).collect(Collectors.toList())
        );
    }

    @PostMapping("juegos/{juegoId}/iniciar")
    private ResponseEntity<JuegoIniciadoRepresentation> iniciarJuego(@PathVariable Integer juegoId) {
        return ResponseEntity.ok(
                juegoService.iniciarJuego(juegoId).iniciadoRepresentation()
        );
    }
}
