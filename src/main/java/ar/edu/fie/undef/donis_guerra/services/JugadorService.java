package ar.edu.fie.undef.donis_guerra.services;

import ar.edu.fie.undef.donis_guerra.entities.Jugador;
import ar.edu.fie.undef.donis_guerra.requests.JugadorRequest;

public interface JugadorService {
    Jugador create(JugadorRequest jugador);
    Jugador findById(Integer jugadorId);
}
