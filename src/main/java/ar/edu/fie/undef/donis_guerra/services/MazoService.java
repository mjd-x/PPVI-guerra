package ar.edu.fie.undef.donis_guerra.services;

import ar.edu.fie.undef.donis_guerra.entities.Mazo;
import ar.edu.fie.undef.donis_guerra.requests.MazoRequest;

public interface MazoService {
    Mazo create(MazoRequest mazo);
    Mazo findById(Integer mazoId);
    Mazo findByJugadorId(Integer jugadorId);
}
