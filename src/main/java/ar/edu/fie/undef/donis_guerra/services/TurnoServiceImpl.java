package ar.edu.fie.undef.donis_guerra.services;

import ar.edu.fie.undef.donis_guerra.entities.Turno;
import ar.edu.fie.undef.donis_guerra.exceptions.NotFoundException;
import ar.edu.fie.undef.donis_guerra.repositories.TurnoRepository;
import ar.edu.fie.undef.donis_guerra.requests.TurnoRequest;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

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
    public Optional<Turno> findByIdOrNull(Integer turnoId) {
        return turnoRepository.findById(turnoId);
    }

    @Override
    public Turno findById(Integer turnoId) {
        return findByIdOrNull(turnoId)
                .orElseThrow(() -> new NotFoundException("No se encontro el turno " + turnoId));
    }

    @Override
    public List<Turno> findByJuegoId(Integer juegoId) {
        return juegoService.findById(juegoId).getTurnos();
    }
}
