package ar.edu.fie.undef.donis_guerra.controllers;

import ar.edu.fie.undef.donis_guerra.entities.Carta;
import ar.edu.fie.undef.donis_guerra.representations.CartaRepresentation;
import ar.edu.fie.undef.donis_guerra.services.CartaService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class CartaController {
    private final CartaService cartaService;
    private final Logger logger = LoggerFactory.getLogger(CartaController.class);

    public CartaController(CartaService cartaService) {
        this.cartaService = cartaService;
    }

    // Buscar cartas en un mazo
    @GetMapping("mazos/{mazoId}/cartas")
    private ResponseEntity<List<CartaRepresentation>> findByMazoId(@PathVariable Integer mazoId) {
        logger.info("Mostrando cartas en mazo " + mazoId);
        return ResponseEntity.ok(
            cartaService.findByMazoId(mazoId).stream()
                        .map(Carta::representation).collect(Collectors.toList())
        );
    }

    // Buscar cartas en el submazo de un jugador
    @GetMapping("jugadores/{jugadorId}/mazo/cartas")
    private ResponseEntity<List<CartaRepresentation>> findByJugadorId(@PathVariable Integer jugadorId) {
        logger.info("Mostrando cartas del jugador " + jugadorId);
        return ResponseEntity.ok(
                cartaService.findByJugadorId(jugadorId).stream()
                        .map(Carta::representation).collect(Collectors.toList())
        );
    }
}
