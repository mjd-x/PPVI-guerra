package ar.edu.fie.undef.donis_guerra.entities;

import ar.edu.fie.undef.donis_guerra.representations.JuegoRepresentation;
import ar.edu.fie.undef.donis_guerra.representations.JuegoMessageRepresentation;
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
    @JoinColumn(name = "mazo_id")
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

    public Juego(String identificacion, List<Jugador> jugadores) {
        this.identificacion = identificacion;
        this.jugadores = jugadores;
    }

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

    //*****************************************************
    // REPRESENTACIONES
    //*****************************************************

    /**
     * Representacion de un juego
     * @return JuegoRepresentation
     **/
    public JuegoRepresentation representation() {
        return new JuegoRepresentation(id, identificacion);
    }

    /**
     * Muestra que se repartio el mazo
     * @return JuegoMessageRepresentation
     **/
    public JuegoMessageRepresentation iniciadoRepresentation() {
        return new JuegoMessageRepresentation("Se repartio el mazo entre los jugadores",
                id, identificacion);
    }

    /**
     * Muestra el paso de un turno
     * @return JuegoMessageRepresentation
     **/
    public JuegoMessageRepresentation pasarTurnoRepresentation() {
        if (getJugadoresActivos().size() == 1) {
            return new JuegoMessageRepresentation(
                    "No se paso ningun turno porque el juego ya termino." +
                            "El ganador de este juego es " + getJugadoresActivos().get(0).getNombre(),
                    id,
                    identificacion);
        } else {
            return new JuegoMessageRepresentation(
                    "Paso un turno, quedan " + getJugadoresActivos().size() + " jugadores activos",
                    id,
                    identificacion);
        }
    }

    /**
     * Muestra el paso de varios turnos
     * @return JuegoMessageRepresentation
     **/
    public JuegoMessageRepresentation pasarVariosTurnosRepresentation(Integer cantidad) {
        if (getJugadoresActivos().size() == 1) {
            return new JuegoMessageRepresentation(
                    "No se paso ningun turno porque el juego ya termino. " +
                            "El ganador de este juego es " + getJugadoresActivos().get(0).getNombre(),
                    id,
                    identificacion);
        } else {
            return new JuegoMessageRepresentation(
                    "Pasaron " + cantidad + " turnos, quedan "
                            + getJugadoresActivos().size() + " jugadores activos",
                    id,
                    identificacion);
        }
    }

    //*****************************************************
    // LOGICA
    //*****************************************************

    /**
     * Devuelve los jugadores activos en un juego
     * @return lista de jugadores activos
     **/
    public List<Jugador> getJugadoresActivos() {
        return  jugadores.stream()
                .filter(Jugador::isActivo)
                .collect(Collectors.toList());
    }

    /**
     * Inicia un juego existente repartiendo
     * el mazo entre los jugadores y marcando
     * a todos como activos
     * @return Juego
     **/
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
