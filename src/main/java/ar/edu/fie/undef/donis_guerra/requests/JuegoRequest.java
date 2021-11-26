package ar.edu.fie.undef.donis_guerra.requests;

import ar.edu.fie.undef.donis_guerra.entities.Juego;
import ar.edu.fie.undef.donis_guerra.entities.Mazo;

import java.util.List;
import java.util.stream.Collectors;

public class JuegoRequest {
    private String identificacion;
    private List<JugadorRequest> jugadores;

    public JuegoRequest(String identificacion, List<JugadorRequest> jugadores) {
        this.identificacion = identificacion;
        this.jugadores = jugadores;
    }

    public String getIdentificacion() {
        return identificacion;
    }

    public void setIdentificacion(String identificacion) {
        this.identificacion = identificacion;
    }

    public List<JugadorRequest> getJugadores() {
        return jugadores;
    }

    public void setJugadores(List<JugadorRequest> jugadores) {
        this.jugadores = jugadores;
    }

    public Juego construct() {
        return new Juego(
                identificacion,
                jugadores.stream().map(JugadorRequest::construct).collect(Collectors.toList())
        );
    }
}
