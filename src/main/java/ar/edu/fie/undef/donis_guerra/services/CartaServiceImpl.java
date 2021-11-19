package ar.edu.fie.undef.donis_guerra.services;

import ar.edu.fie.undef.donis_guerra.entities.Carta;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CartaServiceImpl implements CartaService {
    private final MazoService mazoService;
    private final JugadorService jugadorService;

    public CartaServiceImpl(MazoService mazoService, JugadorService jugadorService) {
        this.mazoService = mazoService;
        this.jugadorService = jugadorService;
    }

    @Override
    public List<Carta> findByMazoId(Integer mazoId) {
        return mazoService.findById(mazoId).getCartas();
    }

    @Override
    public List<Carta> findByJugadorId(Integer jugadorId) {
        Integer mazoId = jugadorService.findById(jugadorId).getMazo().getId();
        return mazoService.findById(mazoId).getCartas();
    }
}
