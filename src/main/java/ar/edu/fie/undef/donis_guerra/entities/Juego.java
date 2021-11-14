package ar.edu.fie.undef.donis_guerra.entities;

import ar.edu.fie.undef.donis_guerra.representations.JuegoRepresentation;

import javax.persistence.*;
import java.util.List;

@Entity
public class Juego {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String identificacion;

    @OneToMany  // un juego tiene varios turnos
    @JoinColumn(name = "juego_id")
    private List<Turno> turno;

    @OneToOne(cascade = CascadeType.PERSIST) // un juego tiene un mazo (que despues se reparte)
    @JoinColumn(name = "juego_id")
    private Mazo mazo;  // mazo inicial del juego que se reparte entre los jugadores

    @OneToMany(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "juego_id")
    private List<Jugador> jugadores;

    public Juego(String identificacion, List<Turno> turno, Mazo mazo, List<Jugador> jugadores) {
        this.identificacion = identificacion;
        this.turno = turno;
        this.mazo = mazo;
        this.jugadores = jugadores;
    }

    public Juego() {
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

    public List<Turno> getTurno() {
        return turno;
    }

    public void setTurno(List<Turno> turno) {
        this.turno = turno;
    }

    public Mazo getMazo() {
        return mazo;
    }

    public void setMazo(Mazo mazo) {
        this.mazo = mazo;
    }

    public List<Jugador> getJugadores() {
        return jugadores;
    }

    public void setJugadores(List<Jugador> jugadores) {
        this.jugadores = jugadores;
    }

    public JuegoRepresentation representation() {
        return new JuegoRepresentation(identificacion);
    }
}
