package ar.edu.fie.undef.donis_guerra;

import ar.edu.fie.undef.donis_guerra.entities.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.List;

public class JuegoTest {
    @Nested
    @DisplayName("Given juego con 1 solo jugador activo")
    class GivenJuegoCon1SoloJugadorActivo {

        @Nested
        @DisplayName("When isTerminado")
        class WhenIsTerminado {

            @Test
            @DisplayName("Then devolver true")
            void thenDevolverTrue() {

                //given
                List<Carta> cartas = new ArrayList<>();
                cartas.add(new Carta(2, Palo.BASTO));
                cartas.add(new Carta(5, Palo.ORO));

                Mazo mazo = new Mazo("mazo_test", cartas);

                List<Jugador> jugadores = new ArrayList<>();

                jugadores.add(new Jugador("jug1_test", true, mazo));
                jugadores.add(new Jugador("jug2_test", true,
                        new Mazo("mazo_vacio_test", new ArrayList<>())));

                Juego juego = new Juego("juego_test", jugadores);

                //when
                // marca como inactivo al perdedor
                Jugador.verificarPerdedores(jugadores);
                juego.setJugadores(jugadores);

                //then
                Assertions.assertTrue(juego.isTerminado());
            }
        }
    }

    @Nested
    @DisplayName("Given un juego con 1 jugador activo")
    class GivenUnJuegoCon1JugadorActivo {

        @Nested
        @DisplayName("When getJugadoresActivos")
        class WhenGetJugadoresActivos {

            @Test
            @DisplayName("Then devolver el jugador activo")
            void thenDevolverElJugadorActivo() {

                //given
                List<Carta> cartas = new ArrayList<>();
                cartas.add(new Carta(2, Palo.BASTO));
                cartas.add(new Carta(5, Palo.ORO));

                Mazo mazo = new Mazo("mazo_test", cartas);

                List<Jugador> jugadores = new ArrayList<>();

                jugadores.add(new Jugador("jug1_test", true, mazo));
                jugadores.add(new Jugador("jug2_test", true,
                        new Mazo("mazo_vacio_test", new ArrayList<>())));

                Juego juego = new Juego("juego_test", jugadores);

                //when
                // marca como inactivo al perdedor
                Jugador.verificarPerdedores(jugadores);
                juego.setJugadores(jugadores);

                //then
                Assertions.assertEquals(1, juego.getJugadoresActivos().size());
                Assertions.assertEquals(juego.getJugadores().get(0), juego.getJugadoresActivos().get(0));
            }
        }
    }

    @Nested
    @DisplayName("Given nuevo juego")
    class GivenNuevoJuego {

        @Nested
        @DisplayName("When iniciarJuego")
        class WhenIniciarJuego {

            @Test
            @DisplayName("Then inicia el juego")
            void thenIniciaElJuego() {

                //given
                List<Carta> cartas = new ArrayList<>();
                cartas.add(new Carta(2, Palo.BASTO));
                cartas.add(new Carta(5, Palo.ORO));
                cartas.add(new Carta(8, Palo.ESPADA));
                cartas.add(new Carta(12, Palo.COPA));

                Mazo mazo = new Mazo("mazo_test", cartas);

                List<Jugador> jugadores = new ArrayList<>();

                jugadores.add(new Jugador("jug1_test"));
                jugadores.add(new Jugador("jug2_test"));

                Juego juego = new Juego("juego_test", mazo, jugadores);

                //when
                juego.iniciarJuego();

                //then
                juego.getJugadores().forEach(jugador -> Assertions.assertTrue(jugador.isActivo()));
                juego.getJugadores().forEach(jugador ->
                        Assertions.assertEquals(2, jugador.getMazo().getCartas().size()));
            }
        }
    }
}
