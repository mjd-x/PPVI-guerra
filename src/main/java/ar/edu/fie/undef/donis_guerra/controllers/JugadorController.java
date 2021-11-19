package ar.edu.fie.undef.donis_guerra.controllers;

import ar.edu.fie.undef.donis_guerra.representations.JugadorRepresentation;
import ar.edu.fie.undef.donis_guerra.requests.JugadorRequest;
import ar.edu.fie.undef.donis_guerra.services.JugadorService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

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


}
