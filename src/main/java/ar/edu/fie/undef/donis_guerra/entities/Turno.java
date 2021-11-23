package ar.edu.fie.undef.donis_guerra.entities;

import ar.edu.fie.undef.donis_guerra.representations.TurnoRepresentation;

import javax.persistence.*;
import java.util.List;

@Entity
public class Turno {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String identificacion;

    @OneToMany(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "turno_id")
    private List<Jugador> jugadores;

    public Turno(String identificacion, List<Jugador> jugadores) {
        this.identificacion = identificacion;
        this.jugadores = jugadores;
    }

    public Turno() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getIdentificacion() {
        return identificacion;
    }

    public void setIdentificacion(String identificacion) {
        this.identificacion = identificacion;
    }

    public List<Jugador> getJugadores() {
        return jugadores;
    }

    public void setJugadores(List<Jugador> jugadores) {
        this.jugadores = jugadores;
    }

    public TurnoRepresentation representation() {
        return new TurnoRepresentation(id, identificacion);
    }
}
