package ar.edu.fie.undef.donis_guerra.services;

import ar.edu.fie.undef.donis_guerra.entities.Turno;
import ar.edu.fie.undef.donis_guerra.requests.TurnoRequest;

import java.util.List;
import java.util.Optional;

public interface TurnoService {
    Turno create(TurnoRequest turno);
    Optional<Turno> findByIdOrNull(Integer turnoId);
    Turno findById(Integer turnoId);
    List<Turno> findByJuegoId(Integer juegoId);
}
