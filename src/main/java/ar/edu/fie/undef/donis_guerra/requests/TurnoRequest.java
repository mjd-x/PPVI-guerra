package ar.edu.fie.undef.donis_guerra.requests;

import ar.edu.fie.undef.donis_guerra.entities.Turno;

public class TurnoRequest {
    private Integer numeroJugadores;

    public TurnoRequest(Integer numeroJugadores) {
        this.numeroJugadores = numeroJugadores;
    }

    public Integer getNumeroJugadores() {
        return numeroJugadores;
    }

    public void setNumeroJugadores(Integer numeroJugadores) {
        this.numeroJugadores = numeroJugadores;
    }

    public Turno construct() {
        return new Turno(numeroJugadores);
    }
}
