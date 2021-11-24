package ar.edu.fie.undef.donis_guerra.services;

import ar.edu.fie.undef.donis_guerra.entities.Juego;
import ar.edu.fie.undef.donis_guerra.repositories.JuegoRepository;
import ar.edu.fie.undef.donis_guerra.requests.JuegoRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class JuegoServiceImpl implements JuegoService {
    private final JuegoRepository juegoRepository;

    public JuegoServiceImpl(JuegoRepository juegoRepository) {
        this.juegoRepository = juegoRepository;
    }

    @Override
    public Juego create(JuegoRequest juego) {
        return juegoRepository.save(juego.construct());
    }

    @Override
    public Juego findById(Integer juegoId) {
        return juegoRepository.findById(juegoId).get();
    }

    @Override
    public List<Juego> findAllByTerminado() {
        return juegoRepository.findAll()
                .stream().filter(juego -> juego.getJugadoresActivos() == 1)
                .collect(Collectors.toList());
    }

    @Override
    public Juego iniciarJuego(Integer juegoId) {
        return juegoRepository.save(findById(juegoId).iniciarJuego());
    }
}
