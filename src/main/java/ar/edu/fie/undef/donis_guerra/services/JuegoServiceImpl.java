package ar.edu.fie.undef.donis_guerra.services;

import ar.edu.fie.undef.donis_guerra.entities.Juego;
import ar.edu.fie.undef.donis_guerra.exceptions.NotFoundException;
import ar.edu.fie.undef.donis_guerra.repositories.JuegoRepository;
import ar.edu.fie.undef.donis_guerra.requests.JuegoRequest;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

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

    @Override
    public List<Juego> findAllByTerminado() {
        return juegoRepository.findAll()
                .stream().filter(juego -> juego.getJugadoresActivos() == 1)
                .collect(Collectors.toList());
    }

    @Override
    public Juego cargarMazo(Integer juegoId) {
        Juego juego = findById(juegoId);
        juego.setMazo(mazoService.clonarInicial());
        return juegoRepository.save(juego);
    }


    @Override
    public Juego iniciarJuego(Integer juegoId) {
        // busca el juego
        Juego juego = cargarMazo(juegoId);

        // le asigna una copia del mazo inicial
//        juego.setMazo(mazoService.clonarInicial());
//        // guardo el juego con el mazo
//        juegoRepository.save(juego);
        //incia el juego
        return juegoRepository.save(juego.iniciarJuego());
    }
}
