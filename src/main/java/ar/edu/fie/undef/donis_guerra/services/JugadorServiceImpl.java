package ar.edu.fie.undef.donis_guerra.services;

import ar.edu.fie.undef.donis_guerra.entities.Jugador;
import ar.edu.fie.undef.donis_guerra.exceptions.NotFoundException;
import ar.edu.fie.undef.donis_guerra.repositories.JugadorRepository;
import ar.edu.fie.undef.donis_guerra.requests.JugadorRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class JugadorServiceImpl implements JugadorService {
    private final JugadorRepository jugadorRepository;
    private final TurnoService turnoService;
    private final JuegoService juegoService;

    public JugadorServiceImpl(JugadorRepository jugadorRepository, TurnoService turnoService, JuegoService juegoService) {
        this.jugadorRepository = jugadorRepository;
        this.turnoService = turnoService;
        this.juegoService = juegoService;
    }

    @Override
    public Jugador create(JugadorRequest jugador) {
        return jugadorRepository.save(jugador.construct());
    }

    @Override
    public Optional<Jugador> findByIdOrNull(Integer jugadorId) {
        return jugadorRepository.findById(jugadorId);
    }

    @Override
    public Jugador findById(Integer jugadorId) {
        return findByIdOrNull(jugadorId)
                .orElseThrow(() -> new NotFoundException("No se encontro el jugador " + jugadorId));
    }

    @Override
    public List<Jugador> findByActivo(boolean activo) {
        return jugadorRepository.findJugadorByActivo(activo);
    }

    @Override
    public List<Jugador> findByTurnoId(Integer turnoId) {
        return turnoService.findById(turnoId).getJugadores();
    }

    @Override
    public List<Jugador> findActivoByTurnoId(Integer turnoId, Boolean activo) {
        List<Jugador> jugadores = findByTurnoId(turnoId);
        return jugadores.stream()
                .filter(jugador -> jugador.isActivo() == activo)
                .collect(Collectors.toList());
    }

    @Override
    public List<Jugador> findByJuegoId(Integer juegoId) {
        return juegoService.findById(juegoId).getJugadores();
    }

    @Override
    public List<Jugador> findActivobyJuegoId(Integer juegoId, Boolean activo) {
        List<Jugador> jugadores = findByJuegoId(juegoId);
        return jugadores.stream()
                .filter(jugador -> jugador.isActivo() == activo)
                .collect(Collectors.toList());
    }

}
