package ar.edu.fie.undef.donis_guerra.services;

import ar.edu.fie.undef.donis_guerra.entities.Turno;
import ar.edu.fie.undef.donis_guerra.repositories.TurnoRepository;
import ar.edu.fie.undef.donis_guerra.requests.TurnoRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TurnoServiceImpl implements TurnoService {
    private final TurnoRepository turnoRepository;
    private final JuegoService juegoService;

    public TurnoServiceImpl(TurnoRepository turnoRepository, JuegoService juegoService) {
        this.turnoRepository = turnoRepository;
        this.juegoService = juegoService;
    }

    @Override
    public Turno create(TurnoRequest turno) {
        return turnoRepository.save(turno.construct());
    }

    @Override
    public Turno findById(Integer turnoId) {
        return turnoRepository.findById(turnoId).get();
    }

    @Override
    public List<Turno> findByJuegoId(Integer juegoId) {
        return juegoService.findById(juegoId).getTurnos();
    }
}
