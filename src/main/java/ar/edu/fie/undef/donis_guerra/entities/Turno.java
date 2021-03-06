package ar.edu.fie.undef.donis_guerra.entities;

import ar.edu.fie.undef.donis_guerra.representations.TurnoRepresentation;

import javax.persistence.*;
import java.util.ArrayList;
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

    @OneToMany(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "turno_id")
    private List<Carta> cartasTurno;

    @OneToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "ganador_id")
    private Jugador jugadorGanador;

    private String nombreGanador;

    private Long jugadoresActivos;

    public Turno() {
    }

    public Turno(String identificacion, List<Jugador> jugadores) {
        this.identificacion = identificacion;
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

    public List<Jugador> getJugadores() {
        return jugadores;
    }

    public void setJugadores(List<Jugador> jugadores) {
        this.jugadores = jugadores;
    }

    public List<Carta> getCartasTurno() {
        return cartasTurno;
    }

    public void setCartasTurno(List<Carta> cartasTurno) {
        this.cartasTurno = cartasTurno;
    }

    public Jugador getJugadorGanador() {
        return jugadorGanador;
    }

    public void setJugadorGanador(Jugador jugadorGanador) {
        this.jugadorGanador = jugadorGanador;
    }

    public String getNombreGanador() {
        return nombreGanador;
    }

    public void setNombreGanador(String nombreGanador) {
        this.nombreGanador = nombreGanador;
    }

    public Long getJugadoresActivos() {
        return jugadoresActivos;
    }

    public void setJugadoresActivos(Long jugadoresActivos) {
        this.jugadoresActivos = jugadoresActivos;
    }

    //*****************************************************
    // REPRESENTACIONES
    //*****************************************************

    /**
     * Representacion de un turno
     * @return TurnoRepresentation
     **/
    public TurnoRepresentation representation() {
        if (jugadoresActivos == 1) {
            return new TurnoRepresentation(
                    "El juego termino",
                    id,
                    identificacion,
                    jugadoresActivos,
                    nombreGanador
            );
        } else {
            return new TurnoRepresentation(
                    "Paso un turno",
                    id,
                    identificacion,
                    jugadoresActivos,
                    nombreGanador
            );
        }
    }

    public TurnoRepresentation pasarVariosRepresentation(Integer cantidad) {
        if (jugadoresActivos == 1) {
            return new TurnoRepresentation(
                    "El juego termino",
                    id,
                    identificacion,
                    jugadoresActivos,
                    nombreGanador
            );
        } else {
            return new TurnoRepresentation(
                    "Pasaron " + cantidad + " turnos",
                    id,
                    identificacion,
                    jugadoresActivos,
                    nombreGanador
            );
        }
    }

    //*****************************************************
    // LOGICA
    //*****************************************************

    /**
     * Verifica si hay un empate entre dos jugadores
     * o alguno de los dos perdio durante el desempate
     * @return boolean
     **/
    public boolean hayEmpate(Carta cartaMayor, Carta cartaActual, Jugador jugador) {
        return cartaMayor.getNumero().equals(cartaActual.getNumero())
                && !jugadorGanador.getMazo().estaVacio()
                && !jugador.getMazo().estaVacio();
    }

    /**
     * Verifica si la carta de un jugador es mayor
     * a la carta del jugador que venia ganando hasta ahora
     * o si el jugador que venia ganando se quedo sin mazo
     * durante el desempate
     * @return boolean
     **/
    public boolean hayCartaMayor(Carta cartaMayor, Carta cartaActual) {
        return cartaActual.getNumero() > cartaMayor.getNumero();
    }

    /**
     * Pasa un turno del juego comparando las primeras cartas
     * de los mazos de todos los jugadores
     * dandole todas las cartas que sacaron al ganador del turno
     * @return Turno
     **/
    public Turno pasarTurno() {
        //*****************************************************************
        // INICIALIZA EL TURNO
        //*****************************************************************

        this.cartasTurno = new ArrayList<>();

        // busca el primer jugador y su carta para comparar con el siguiente
        Carta cartaMayor = jugadores.get(0).getMazo().primeraCarta();
        this.jugadorGanador = jugadores.get(0);
        // agrega la carta al loot del turno
        cartasTurno.add(cartaMayor);

        //*****************************************************************
        // EMPIEZA EL TURNO (COMPARA LAS CARTAS)
        //*****************************************************************

        for (int i = 1; i < jugadores.size(); i++) {
            Jugador jugador = jugadores.get(i);

            // busca la primer carta del jugador actual para comparar con la mayor (hasta ahora)
            Carta cartaActual = jugador.getMazo().primeraCarta();
            // agrega la carta al loot del turno
            cartasTurno.add(cartaActual);

            //*****************************************************
            // SI HAY EMPATE
            //*****************************************************

            while (hayEmpate(cartaMayor, cartaActual, jugador)) {
                // sigue sacando cartas de los dos jugadores empatados hasta que desempaten
                cartaMayor = jugadorGanador.getMazo().primeraCarta();
                cartaActual = jugador.getMazo().primeraCarta();

                // agrega las dos cartas al loot del turno
                cartasTurno.add(cartaMayor);
                cartasTurno.add(cartaActual);
            }

            //*****************************************************
            // SI UN JUGADOR SACO UNA CARTA MAS ALTA QUE LA INICIAL
            //*****************************************************

            if (hayCartaMayor(cartaMayor, cartaActual)) {
                // la carta de este jugador es mayor que la que esta ahora como mayor
                // cambia la carta que esta ganando
                cartaMayor = cartaActual;

                // verifica si quien era el jugador ganador (que ahora perdio) se quedo sin cartas
                // (para caso de desempate donde se quedo sin cartas el que venia ganando)
                if (jugadorGanador.getMazo().estaVacio()) {
                    jugadorGanador.setActivo(false);
                }

                // cambia el jugador que viene ganando (que saco la carta mas alta)
                jugadorGanador = jugador;
            }
        }

        //*****************************************************************
        // DESPUES DE COMPARAR LAS CARTAS DE TODOS LOS JUGADORES
        //*****************************************************************

        // busca el mazo del jugador que gano el turno (saco la carta mas alta)
        Mazo mazo = jugadorGanador.getMazo();
        // le agrega al mazo del ganador del turno las cartas del loot (clonadas al final de su mazo)
        mazo.agregarCartas(cartasTurno);
        jugadorGanador.setMazo(mazo);
        // guardo el nombre del ganador (para mostrar mas adelante)
        nombreGanador = jugadorGanador.getNombre();

        // verifica si algun jugador perdio (saco su ultima carta del mazo)
        Jugador.verificarPerdedores(jugadores);

        jugadoresActivos = jugadores.stream().filter(Jugador::isActivo).count();

        return this;
    }
}
