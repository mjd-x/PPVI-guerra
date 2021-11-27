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

    @Override
    public List<Juego> findAllByTerminado() {
        return juegoRepository.findAll()
                .stream().filter(juego -> juego.getJugadoresActivos().size() == 1)
                .collect(Collectors.toList());
    }

    @Override
    public Juego cargarMazo(Integer juegoId) {
        // clona el mazo incial en un juego
        Juego juego = findById(juegoId);
        juego.setMazo(mazoService.clonarInicial());
        return juegoRepository.save(juego);
    }

    @Override
    public Juego iniciarJuego(Integer juegoId) {
        // busca el juego y le carga el mazo inicial
        Juego juego = cargarMazo(juegoId);

        //incia el juego
        return juegoRepository.save(juego.iniciarJuego());
    }

    // TODO nunca termina el juego porque me ordena automaticamente las listas por id
    // como se crean en orden del 1 al 12 siempre termina ciclando porque tienen las cartas ordenadas

    @Override
    public Juego pasarTurno(Integer juegoId) {
        Juego juego = findById(juegoId);

        // solo busca a los jugadores activos (todavia no perdieron)
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

        // cicla por todos los jugadores activos
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

            if (cartaActual.getNumero() > cartaMayor.getNumero()) {
                // la carta de este jugador es mayor que la que esta ahora
                // cambia el jugador y carta que esta ganando
                cartaMayor = cartaActual;
                jugadorGanador = jugador;

            }

            // verifica si alguno perdio (saco su ultima carta)
            if (jugador.getMazo().estaVacio()) {
                jugador.setActivo(false);
            }
        }

        Mazo mazo = jugadorGanador.getMazo();
        mazo.agregarCartas(cartasJugadores);
        jugadorGanador.setMazo(mazo);

        return juegoRepository.save(juego);
    }

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
