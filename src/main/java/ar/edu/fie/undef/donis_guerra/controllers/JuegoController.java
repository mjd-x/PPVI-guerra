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
    @GetMapping("juegos")
    private ResponseEntity<List<JuegoRepresentation>> findAllByTerminado(
            @RequestParam boolean terminado) {
        return ResponseEntity.ok(
                juegoService.findAllByTerminado(terminado).stream()
                        .map(Juego::representation).collect(Collectors.toList())
        );
    }

    // Iniciar un juego que ya existe
    @PostMapping("juegos/{juegoId}/inicio")
    private ResponseEntity<JuegoMessageRepresentation> iniciarJuego(@PathVariable Integer juegoId) {
        return ResponseEntity.ok(
                juegoService.iniciarJuego(juegoId).iniciadoRepresentation()
        );
    }
}
