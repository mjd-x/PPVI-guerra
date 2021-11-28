package ar.edu.fie.undef.donis_guerra.services;

import ar.edu.fie.undef.donis_guerra.entities.Juego;
import ar.edu.fie.undef.donis_guerra.entities.Jugador;
import ar.edu.fie.undef.donis_guerra.entities.Turno;
import ar.edu.fie.undef.donis_guerra.exceptions.NotFoundException;
import ar.edu.fie.undef.donis_guerra.repositories.TurnoRepository;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
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

    /**
     * Pasa un turno en el juego, todos los jugadores sacan
     * la primera carta de su mazo y el que tenga la mayor
     * se guarda todas las cartas
     **/
    @Override
    public Turno pasarTurno(Integer juegoId) {
        Juego juego = juegoService.findById(juegoId);
        List<Jugador> jugadores = juego.getJugadoresActivos();

        if (juego.isTerminado()) {
            // si se termino el juego, no pasa de turno (avisa en la representation)
            return null;
        } else {

            Turno turno = new Turno("turno_juego" + juegoId, jugadores);
            turno.pasarTurno();
            turno = turnoRepository.save(turno);

            List<Turno> turnos = juego.getTurnos();
            turnos.add(turno);
            juego.setTurnos(turnos);

            juegoService.save(juego);

            return turno;
        }
    }

    /**
     * Pasar varios turnos a la vez
     **/
    @Override
    public List<Turno> pasarVariosTurnos(Integer juegoId, Integer cantidad) {
        Juego juego = juegoService.findById(juegoId);
        List<Turno> turnos = new ArrayList<>();

        // pasa la cantidad de turnos que mando
        for (int i = 0; i < cantidad ; i++) {
            if (juego.isTerminado()) {
                break;
            }
            turnos.add(pasarTurno(juegoId));
        }

        return turnos;
    }
}
