package ar.edu.fie.undef.donis_guerra.services;

import ar.edu.fie.undef.donis_guerra.entities.Carta;
import ar.edu.fie.undef.donis_guerra.entities.Juego;
import ar.edu.fie.undef.donis_guerra.entities.Jugador;
import ar.edu.fie.undef.donis_guerra.entities.Mazo;
import ar.edu.fie.undef.donis_guerra.exceptions.NotFoundException;
import ar.edu.fie.undef.donis_guerra.repositories.JuegoRepository;
import ar.edu.fie.undef.donis_guerra.requests.JuegoRequest;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class JuegoServiceImpl implements JuegoService {
    private final JuegoRepository juegoRepository;
    private final MazoService mazoService;

    public JuegoServiceImpl(JuegoRepository juegoRepository, @Lazy MazoService mazoService) {
        this.juegoRepository = juegoRepository;
        this.mazoService = mazoService;
    }

    @Override
    public Juego create(JuegoRequest juego) {
        return juegoRepository.save(juego.construct());
    }

    @Override
    public Optional<Juego> findByIdOrNull(Integer juegoId) {
        return juegoRepository.findById(juegoId);
    }

    @Override
    public Juego findById(Integer juegoId) {
        return findByIdOrNull(juegoId)
                .orElseThrow(() -> new NotFoundException("No se encontro el juego " + juegoId));
    }

    /**
     * Busca los juegos que tienen un ganador
     **/
    @Override
    public List<Juego> findAllByTerminado() {
        return juegoRepository.findAll()
                .stream().filter(juego -> juego.getJugadoresActivos().size() == 1)
                .collect(Collectors.toList());
    }

    /**
     * Clona el mazo inicial y lo carga como mazo del juego
     **/
    @Override
    public Juego cargarMazo(Integer juegoId) {
        // clona el mazo incial en un juego
        Juego juego = findById(juegoId);
        juego.setMazo(mazoService.clonarInicial());
        return juegoRepository.save(juego);
    }

    /**
     * Inicia un juego existente
     **/
    @Override
    public Juego iniciarJuego(Integer juegoId) {
        // busca el juego y le carga el mazo inicial
        Juego juego = cargarMazo(juegoId);

        //incia el juego
        return juegoRepository.save(juego.iniciarJuego());
    }

    /**
     * Pasa un turno en el juego, todos los jugadores sacan
     * la primera carta de su mazo y el que tenga la mayor
     * se guarda todas las cartas
     **/
    @Override
    public Juego pasarTurno(Integer juegoId) {
        Juego juego = findById(juegoId);

        // solo busca a los jugadores activos (los que todavia no perdieron)
        List<Jugador> jugadores = juego.getJugadoresActivos();

        // verifica si se termino el juego (1 solo jugador activo)
        if (jugadores.size() == 1){
            return juego;
        }

        // lista para guardar todas las primeras cartas de los jugadores
        // cuando termina el turno, el jugador ganador se guarda todas las cartas
        List<Carta> cartasJugadores = new ArrayList<>();

        // busca el primer jugador y su carta para comparar con el siguiente
        Carta cartaMayor = jugadores.get(0).getMazo().primeraCarta();
        cartasJugadores.add(cartaMayor);
        Jugador jugadorGanador = jugadores.get(0);

        Iterator<Jugador> jugadorIterator = jugadores.iterator();
        // paso el primero porque ya lo tome mas arriba
        jugadorIterator.next();

        // cicla por todos los demas jugadores activos
        while (jugadorIterator.hasNext()) {
            Jugador jugador = jugadorIterator.next();

            // busca la primer carta del jugador actual para comparar con la mayor (hasta ahora)
            Carta cartaActual = jugador.getMazo().primeraCarta();
            // agrega la carta al loot del turno
            cartasJugadores.add(cartaActual);

            // verifica si hay un empate (las dos cartas son del mismo valor)
            // ademas verifica si durante el desempate algun jugador perdio (se quedo sin cartas)
            while (cartaMayor.getNumero().equals(cartaActual.getNumero())
                    && !jugadorGanador.getMazo().estaVacio()
                    && !jugador.getMazo().estaVacio())
            {
                // hay un empate
                // sigue sacando cartas de los dos jugadores empatados hasta que desempaten
                cartaMayor = jugadorGanador.getMazo().primeraCarta();
                cartaActual = jugador.getMazo().primeraCarta();

                cartasJugadores.add(cartaMayor);
                cartasJugadores.add(cartaActual);
            }

            // si otro jugador saca una carta mayor
            // o durante el desempate el jugador que venia ganando se queda sin cartas
            if (cartaActual.getNumero() > cartaMayor.getNumero()
                    || jugadorGanador.getMazo().estaVacio())
            {
                // la carta de este jugador es mayor que la que esta ahora
                // cambia el jugador y carta que esta ganando
                cartaMayor = cartaActual;

                // verifica si quien era el jugador ganador se quedo sin cartas
                // (para caso de desempate donde se quedo sin cartas el que venia ganando)
                if (jugadorGanador.getMazo().estaVacio()) {
                    jugadorGanador.setActivo(false);
                }

                // cambia el jugador que viene ganando
                jugadorGanador = jugador;
            }
        }

        Mazo mazo = jugadorGanador.getMazo();

        // para agregar al mazo las cartas de la mesa
        // clona la lista de cartas y las agrega de vuelta para que esten en el fondo del mazo
        // necesita borrar las cartas en cartasJugadores porque las clona cuando usa agregarCartas()
        // si no las borra, quedan muchas cartas con mazo_id = null
        // entonces las agrega a un mazo de descarte, y borra ese mazo para sacar las cartas que ya no se usan
        Mazo mazoBorrar = mazoService.save(new Mazo("descarte", cartasJugadores));

        mazo.agregarCartas(cartasJugadores);
        mazoService.destroy(mazoBorrar.getId());
        jugadorGanador.setMazo(mazo);

        // verifica si alguno perdio (saco su ultima carta)
        for (Jugador jugador : jugadores) {
            if (jugador.getMazo().estaVacio()) {
                jugador.setActivo(false);
            }
        }

        return juegoRepository.save(juego);
    }

    /**
     * Pasar varios turnos seguidos
     **/
    @Override
    public Juego pasarVariosTurnos(Integer juegoId, Integer cantidad) {
        Juego juego = findById(juegoId);

        // pasa la cantidad de turnos que mando
        for (int i = 0; i < cantidad ; i++) {
            pasarTurno(juegoId);
        }

        return juego;
    }
}
