package ar.edu.fie.undef.donis_guerra.services;

import ar.edu.fie.undef.donis_guerra.entities.Turno;
import ar.edu.fie.undef.donis_guerra.repositories.TurnoRepository;
import ar.edu.fie.undef.donis_guerra.requests.TurnoRequest;
import org.springframework.stereotype.Service;

@Service
public class TurnoServiceImpl implements TurnoService {
    private final TurnoRepository turnoRepository;

    public TurnoServiceImpl(TurnoRepository turnoRepository) {
        this.turnoRepository = turnoRepository;
    }

    @Override
    public Turno create(TurnoRequest turno) {
        return turnoRepository.save(turno.construct());
    }

    @Override
    public Turno findById(Integer turnoId) {
        return turnoRepository.findById(turnoId).get();
    }
}
