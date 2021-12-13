package ar.edu.fie.undef.donis_guerra.services;

import ar.edu.fie.undef.donis_guerra.entities.*;
import ar.edu.fie.undef.donis_guerra.exceptions.NotFoundException;
import ar.edu.fie.undef.donis_guerra.repositories.JuegoRepository;
import ar.edu.fie.undef.donis_guerra.requests.JuegoRequest;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class JuegoServiceImpl implements JuegoService {
    private final JuegoRepository juegoRepository;
    private final MazoService mazoService;

    public JuegoServiceImpl(JuegoRepository juegoRepository, @Lazy MazoService mazoService) {
        this.juegoRepository = juegoRepository;
        this.mazoService = mazoService;
    }

    @Override
    public Juego create(JuegoRequest juego) {
        return juegoRepository.save(juego.construct());
    }

    @Override
    public Optional<Juego> findByIdOrNull(Integer juegoId) {
        return juegoRepository.findById(juegoId);
    }

    @Override
    public Juego findById(Integer juegoId) {
        return findByIdOrNull(juegoId)
                .orElseThrow(() -> new NotFoundException("No se encontro el juego " + juegoId));
    }

    /**
     * Busca los juegos que tienen un ganador
     **/
    @Override
    public List<Juego> findAllByTerminado(boolean terminado) {
        if (terminado) {
            return juegoRepository.findAll()
                    .stream().filter(juego -> juego.getJugadoresActivos().size() == 1)
                    .collect(Collectors.toList());
        } else
        {
            return juegoRepository.findAll()
                    .stream().filter(juego -> juego.getJugadoresActivos().size() != 1)
                    .collect(Collectors.toList());
        }
    }

    /**
     * Clona el mazo inicial y lo carga como mazo del juego
     **/
    @Override
    public Juego cargarMazo(Integer juegoId) {
        // clona el mazo incial en un juego
        Juego juego = findById(juegoId);
        juego.setMazo(mazoService.clonarInicial());
        return juegoRepository.save(juego);
    }

    /**
     * Inicia un juego existente
     **/
    @Override
    public Juego iniciarJuego(Integer juegoId) {
        // busca el juego y le carga el mazo inicial
        Juego juego = cargarMazo(juegoId);

        //incia el juego
        return juegoRepository.save(juego.iniciarJuego());
    }

    @Override
    public Juego save(Juego juego) {
        return juegoRepository.save(juego);
    }
}
