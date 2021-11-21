package ar.edu.fie.undef.donis_guerra.requests;
import ar.edu.fie.undef.donis_guerra.entities.Turno;

import java.util.List;
import java.util.stream.Collectors;

public class TurnoRequest {
    private String identificacion;
    private List<JugadorRequest> jugadores;

    public TurnoRequest(String identificacion, List<JugadorRequest> jugadores) {
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

    public Turno construct() {
        return new Turno(
                identificacion,
                jugadores.stream().map(JugadorRequest::construct).collect(Collectors.toList())
        );
    }
}
