package ar.edu.fie.undef.donis_guerra.services;

import ar.edu.fie.undef.donis_guerra.entities.Turno;
import java.util.List;
import java.util.Optional;

public interface TurnoService {
    Optional<Turno> findByIdOrNull(Integer turnoId);
    Turno findById(Integer turnoId);
    List<Turno> findByJuegoId(Integer juegoId);
    Turno pasarTurno(Integer juegoId);
    List<Turno> pasarVariosTurnos(Integer juegoId, Integer cantidad);
}
