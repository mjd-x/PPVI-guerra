package ar.edu.fie.undef.donis_guerra.services;

import ar.edu.fie.undef.donis_guerra.entities.Carta;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CartaServiceImpl implements CartaService {
    private final MazoService mazoService;

    public CartaServiceImpl(MazoService mazoService) {
        this.mazoService = mazoService;
    }

    @Override
    public List<Carta> findByMazoId(Integer mazoId) {
        return mazoService.findById(mazoId).getCartas();
    }
}
