package ar.edu.fie.undef.donis_guerra.services;

import ar.edu.fie.undef.donis_guerra.entities.Juego;
import ar.edu.fie.undef.donis_guerra.requests.JuegoRequest;

public interface JuegoService {
    Juego create(JuegoRequest juego);
    Juego findById(Integer juegoId);
}
