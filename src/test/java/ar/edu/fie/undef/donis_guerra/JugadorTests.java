package ar.edu.fie.undef.donis_guerra;

import ar.edu.fie.undef.donis_guerra.entities.Jugador;
import org.junit.jupiter.api.Assertions;
import ar.edu.fie.undef.donis_guerra.entities.Carta;
import ar.edu.fie.undef.donis_guerra.entities.Mazo;
import ar.edu.fie.undef.donis_guerra.entities.Palo;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.List;

public class JugadorTests {
    @Nested
    @DisplayName("Given lista de jugadores")
    class GivenListaDeJugadores {

        @Nested
        @DisplayName("When verificarPerdedores")
        class WhenVerificarPerdedores {

            @Test
            @DisplayName("Then marcar como inactivos a los perdedores")
            void thenMarcarComoInactivosALosPerdedores() {

                //given
                List<Carta> cartas = new ArrayList<>();
                cartas.add(new Carta(2, Palo.BASTO));
                cartas.add(new Carta(5, Palo.ORO));

                Mazo mazo = new Mazo("mazo_test", cartas);

                List<Jugador> jugadores = new ArrayList<>();

                jugadores.add(new Jugador("jug1_test", true, mazo));
                jugadores.add(new Jugador("jug2_test", true,
                        new Mazo("mazo_vacio_test", new ArrayList<>())));

                //when
                Jugador.verificarPerdedores(jugadores);

                //then
                Assertions.assertTrue(jugadores.get(0).isActivo());
                Assertions.assertFalse(jugadores.get(1).isActivo());
            }
        }
    }
}
