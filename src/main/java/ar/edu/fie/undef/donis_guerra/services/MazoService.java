package ar.edu.fie.undef.donis_guerra.services;

import ar.edu.fie.undef.donis_guerra.entities.Carta;
import ar.edu.fie.undef.donis_guerra.entities.Mazo;
import ar.edu.fie.undef.donis_guerra.requests.MazoRequest;

import java.util.Optional;

public interface MazoService {
    Mazo create(MazoRequest mazo);
    Optional<Mazo> findByIdOrNull(Integer mazoId);
    Mazo findById(Integer mazoId);
    Mazo findByJugadorId(Integer jugadorId);
    Mazo save(Mazo mazo);
    Integer count();
    Mazo clonarInicial();
}
