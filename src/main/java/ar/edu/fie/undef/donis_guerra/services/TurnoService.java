package ar.edu.fie.undef.donis_guerra.services;

import ar.edu.fie.undef.donis_guerra.entities.Turno;
import ar.edu.fie.undef.donis_guerra.requests.TurnoRequest;

public interface TurnoService {
    Turno create(TurnoRequest turno);
    Turno findById(Integer turnoId);
}
