package ar.edu.fie.undef.donis_guerra.services;

import ar.edu.fie.undef.donis_guerra.entities.Carta;
import ar.edu.fie.undef.donis_guerra.entities.Mazo;
import ar.edu.fie.undef.donis_guerra.repositories.MazoRepository;
import ar.edu.fie.undef.donis_guerra.requests.MazoRequest;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class MazoServiceImpl implements MazoService {
    private final MazoRepository mazoRepository;
    private final JugadorService jugadorService;


    public MazoServiceImpl(MazoRepository mazoRepository, JugadorService jugadorService) {
        this.mazoRepository = mazoRepository;
        this.jugadorService = jugadorService;
    }

    @Override
    public Mazo create(MazoRequest mazo) {
        return mazoRepository.save(mazo.construct());
    }

    @Override
    public Mazo findById(Integer mazoId) {
        return mazoRepository.findById(mazoId).get();
    }

    @Override
    public Mazo findByJugadorId(Integer jugadorId) {
        return jugadorService.findById(jugadorId).getMazo();
    }

    @Override
    public Mazo mezclar(Integer mazoId) {
        Mazo mazo = findById(mazoId);
        List<Carta> cartas = mazo.getCartas();
        System.out.println(cartas.stream()
                .map(Carta::getId).collect(Collectors.toList()));
        Collections.shuffle(cartas);
        System.out.println(cartas.stream()
                .map(Carta::getId).collect(Collectors.toList()));
        mazo.setCartas(cartas);
        System.out.println(mazo.getCartas().stream()
                .map(Carta::getId).collect(Collectors.toList()));
        mazoRepository.save(mazo);
        System.out.println(mazo.getCartas().stream()
                .map(Carta::getId).collect(Collectors.toList()));
        return mazoRepository.save(mazo);
    }

    @Override
    public Mazo save(Mazo mazo) {
        return mazoRepository.save(mazo);
    }

    @Override
    public Integer count() {
        return mazoRepository.countAllBy();
    }
}
