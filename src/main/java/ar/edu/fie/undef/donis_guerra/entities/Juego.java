package ar.edu.fie.undef.donis_guerra.entities;

import ar.edu.fie.undef.donis_guerra.representations.JuegoIniciadoRepresentation;
import ar.edu.fie.undef.donis_guerra.representations.JuegoRepresentation;

import javax.persistence.*;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

@Entity
public class Juego {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String identificacion;

    @OneToMany(cascade = CascadeType.PERSIST)  // un juego tiene varios turnos
    @JoinColumn(name = "juego_id")
    private List<Turno> turnos;

    @OneToOne(cascade = CascadeType.PERSIST) // un juego tiene un mazo (que despues se reparte)
    @JoinColumn(name = "juego_id")
    private Mazo mazo;  // mazo inicial del juego que se reparte entre los jugadores

    @OneToMany(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "juego_id")
    private List<Jugador> jugadores;

    public Juego(String identificacion, List<Turno> turnos, Mazo mazo, List<Jugador> jugadores) {
        this.identificacion = identificacion;
        this.turnos = turnos;
        this.mazo = mazo;
        this.jugadores = jugadores;
    }

    public Juego() {
    }

    // otro constructor sin turnos porque no tiene mucho sentido inicializar el juego con turnos
    public Juego(String identificacion, Mazo mazo, List<Jugador> jugadores) {
        this.identificacion = identificacion;
        this.mazo = mazo;
        this.jugadores = jugadores;
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

    public List<Turno> getTurnos() {
        return turnos;
    }

    public void setTurnos(List<Turno> turnos) {
        this.turnos = turnos;
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
        return new JuegoRepresentation(id, identificacion);
    }

    public JuegoIniciadoRepresentation iniciadoRepresentation() {
        return new JuegoIniciadoRepresentation("Se repartio el mazo entre los jugadores",
                id, identificacion);
    }

    public Integer getJugadoresActivos() {
        return (int) jugadores.stream()
                .filter(Jugador::isActivo).count();
    }

    public Juego iniciarJuego() {
        // marca todos los jugadores como activos (empiezan a jugar)
        jugadores.forEach(jugador -> jugador.setActivo(true));

        // trae lista de lista con los submazos para cada jugador
        List<Mazo> mazos = mazo.repartir(jugadores.size());

        // repartir el mazo, itera por las dos listas al mismo tiempo
        Iterator<Jugador> JugadorIterator = jugadores.iterator();
        Iterator<Mazo> MazoIterator = mazos.iterator();

        // asigna un submazo a cada jugador
        while (JugadorIterator.hasNext() && MazoIterator.hasNext()) {
            Jugador jugador = JugadorIterator.next();
            Mazo submazo = MazoIterator.next();

            jugador.setMazo(submazo);
        }

        // devuelve el objeto cambiado
        return this;
    }
}
