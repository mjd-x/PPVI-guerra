package ar.edu.fie.undef.donis_guerra.representations;

public class TurnoRepresentation {
    private Integer id;
    private Integer numeroJugadores;

    public TurnoRepresentation(Integer id, Integer numeroJugadores) {
        this.id = id;
        this.numeroJugadores = numeroJugadores;
    }

    public Integer getNumeroJugadores() {
        return numeroJugadores;
    }

    public void setNumeroJugadores(Integer numeroJugadores) {
        this.numeroJugadores = numeroJugadores;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
