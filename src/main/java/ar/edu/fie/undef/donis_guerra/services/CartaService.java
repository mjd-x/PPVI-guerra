package ar.edu.fie.undef.donis_guerra.services;

import ar.edu.fie.undef.donis_guerra.entities.Carta;

import java.util.List;

public interface CartaService {
    List<Carta> findByMazoId(Integer mazoId);
    List<Carta> findByJugadorId(Integer jugadorId);
}
