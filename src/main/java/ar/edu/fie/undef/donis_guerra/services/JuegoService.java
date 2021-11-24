package ar.edu.fie.undef.donis_guerra.services;

import ar.edu.fie.undef.donis_guerra.entities.Juego;
import ar.edu.fie.undef.donis_guerra.requests.JuegoRequest;

import java.util.List;

public interface JuegoService {
    Juego create(JuegoRequest juego);
    Juego findById(Integer juegoId);
    Juego iniciarJuego(Integer juegoId);
    List<Juego> findAllByTerminado();
}
