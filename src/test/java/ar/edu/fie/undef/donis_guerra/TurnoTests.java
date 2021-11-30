package ar.edu.fie.undef.donis_guerra;

import ar.edu.fie.undef.donis_guerra.entities.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.List;

public class TurnoTests {
    @Nested
    @DisplayName("Given turno con 2 jugadores de cartas de diferente valor")
    class GivenTurnoCon2JugadoresDeCartasDeDiferenteValor {

        @Nested
        @DisplayName("When pasar de turno")
        class WhenPasarDeTurno {

            @Test
            @DisplayName("Then gana la carta mayor")
            void thenGanaLaCartaMayor() {

                //given
                List<Carta> cartas1 = new ArrayList<>();
                cartas1.add(new Carta(2, Palo.BASTO));

                List<Carta> cartas2 = new ArrayList<>();
                cartas2.add(new Carta(5, Palo.ORO));

                Mazo mazo1 = new Mazo("mazo_test1", cartas1);
                Mazo mazo2 = new Mazo("mazo_test2", cartas2);

                List<Jugador> jugadores = new ArrayList<>();

                jugadores.add(new Jugador("jug1_test", true, mazo1));
                jugadores.add(new Jugador("jug2_test", true, mazo2));

                Turno turno = new Turno("turno_test", jugadores);

                //when
                turno.pasarTurno();

                //then
                Assertions.assertEquals(turno.getJugadores().get(1), turno.getJugadorGanador());
                Assertions.assertEquals(turno.getJugadores().get(1).getNombre(), turno.getNombreGanador());
                Assertions.assertEquals(1, turno.getJugadoresActivos());
                Assertions.assertEquals(2, turno.getCartasTurno().size());
            }
        }
    }

    @Nested
    @DisplayName("Given turno con 2 jugadores con la misma carta")
    class GivenTurnoCon2JugadoresConLaMismaCarta {

        @Nested
        @DisplayName("When pasar de turno")
        class WhenPasarDeTurno {

            @Test
            @DisplayName("Then hay desempate")
            void thenHayDesempate() {
                //given
                List<Carta> cartas1 = new ArrayList<>();
                cartas1.add(new Carta(2, Palo.BASTO));
                cartas1.add(new Carta(1, Palo.ESPADA));

                List<Carta> cartas2 = new ArrayList<>();
                cartas2.add(new Carta(2, Palo.ORO));
                cartas2.add(new Carta(5, Palo.COPA));

                Mazo mazo1 = new Mazo("mazo_test1", cartas1);
                Mazo mazo2 = new Mazo("mazo_test2", cartas2);

                List<Jugador> jugadores = new ArrayList<>();

                jugadores.add(new Jugador("jug1_test", true, mazo1));
                jugadores.add(new Jugador("jug2_test", true, mazo2));

                Turno turno = new Turno("empate_test", jugadores);

                //when
                turno.pasarTurno();

                //then
                Assertions.assertEquals(turno.getJugadores().get(1), turno.getJugadorGanador());
                Assertions.assertEquals(turno.getJugadores().get(1).getNombre(), turno.getNombreGanador());
                Assertions.assertEquals(1, turno.getJugadoresActivos());
                Assertions.assertEquals(4, turno.getCartasTurno().size());

            }
        }
    }
}
