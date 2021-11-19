package ar.edu.fie.undef.donis_guerra.services;

import ar.edu.fie.undef.donis_guerra.entities.Jugador;
import ar.edu.fie.undef.donis_guerra.exceptions.NotFoundException;
import ar.edu.fie.undef.donis_guerra.repositories.JugadorRepository;
import ar.edu.fie.undef.donis_guerra.requests.JugadorRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class JugadorServiceImpl implements JugadorService {
    private final JugadorRepository jugadorRepository;

    public JugadorServiceImpl(JugadorRepository jugadorRepository) {
        this.jugadorRepository = jugadorRepository;
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
}
