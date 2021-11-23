package ar.edu.fie.undef.donis_guerra.services;

import ar.edu.fie.undef.donis_guerra.entities.Jugador;
import ar.edu.fie.undef.donis_guerra.requests.JugadorRequest;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public interface JugadorService {
    Jugador create(JugadorRequest jugador);
    Optional<Jugador> findByIdOrNull(Integer jugadorId);
    Jugador findById(Integer jugadorId);
    List<Jugador> findByActivo(boolean activo);
    List<Jugador> findByTurnoId(Integer turnoId);
    List<Jugador> findActivoByTurnoId(Integer turnoId);
    List<Jugador> findByJuegoId(Integer juegoId);
    List<Jugador> findActivobyJuegoId(Integer juegoId);
}
