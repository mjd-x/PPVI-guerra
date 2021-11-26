package ar.edu.fie.undef.donis_guerra.services;

import ar.edu.fie.undef.donis_guerra.entities.Juego;
import ar.edu.fie.undef.donis_guerra.requests.JuegoRequest;

import java.util.List;
import java.util.Optional;

public interface JuegoService {
    Juego create(JuegoRequest juego);
    Optional<Juego> findByIdOrNull(Integer juegoId);
    Juego findById(Integer juegoId);
    Juego iniciarJuego(Integer juegoId);
    List<Juego> findAllByTerminado();
}
