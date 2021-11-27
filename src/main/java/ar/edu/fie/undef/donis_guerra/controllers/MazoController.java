package ar.edu.fie.undef.donis_guerra.controllers;

import ar.edu.fie.undef.donis_guerra.representations.MazoRepresentation;
import ar.edu.fie.undef.donis_guerra.requests.MazoRequest;
import ar.edu.fie.undef.donis_guerra.services.MazoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class MazoController {
    private final MazoService mazoService;

    public MazoController(MazoService mazoService) {
        this.mazoService = mazoService;
    }

    // Crear un mazo
    @PostMapping("mazos")
    private ResponseEntity<MazoRepresentation> create(@RequestBody MazoRequest mazo) {
        return ResponseEntity.ok(
            mazoService.create(mazo).representation()
        );
    }

    // Buscar un mazo
    @GetMapping("mazos/{mazoId}")
    private ResponseEntity<MazoRepresentation> findById(@PathVariable Integer mazoId) {
        return ResponseEntity.ok(
                mazoService.findById(mazoId).representation()
        );
    }

    // Buscar el submazo de un jugador
    @GetMapping("jugadores/{jugadorId}/mazo")
    private ResponseEntity<MazoRepresentation> findByJugadorId(@PathVariable Integer jugadorId) {
        return ResponseEntity.ok(
                mazoService.findByJugadorId(jugadorId).representation()
        );
    }
}
