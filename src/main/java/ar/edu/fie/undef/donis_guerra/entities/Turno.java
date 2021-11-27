package ar.edu.fie.undef.donis_guerra.entities;

import ar.edu.fie.undef.donis_guerra.representations.TurnoRepresentation;

import javax.persistence.*;
import java.util.Dictionary;
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

//    private List<List<String>> jugadores;
    // TODO como defino aca el turno
    // Si yo lo relaciono con jugadores no me va a mantener el estado en cada turno (historial)
    // sino que a medida que avance el juego y cambien los atributos de los jugadores
    // en cualquier turno que acceda voy a ver los datos actuales del jugador
    // y no los historicos del turno en ese momento

    // se me ocurre guardar:
    // List<List<String>> -> [ [idJugador1, nombreJugador1, cartasJugador1], [...] ... ]
    // pero no puedo
    // si lo hago asi, en el serviceImpl de juego creo un turno cada vez que paso uno
    // guardando esta informacion para que se pueda consultar que paso en cada turno

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
