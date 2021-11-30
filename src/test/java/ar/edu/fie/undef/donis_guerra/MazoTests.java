package ar.edu.fie.undef.donis_guerra;

import org.junit.jupiter.api.Assertions;
import ar.edu.fie.undef.donis_guerra.entities.Carta;
import ar.edu.fie.undef.donis_guerra.entities.Mazo;
import ar.edu.fie.undef.donis_guerra.entities.Palo;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.List;

public class MazoTests {
    @Nested
    @DisplayName("Given un mazo")
    class GivenUnMazo {

        @Nested
        @DisplayName("When primeraCarta")
        class WhenPrimeraCarta {

            @Test
            @DisplayName("Then sacar primera carta del mazo")
            void thenSacarPrimeraCartaDelMazo() {

                //given
                List<Carta> cartas = new ArrayList<>();
                cartas.add(new Carta(2, Palo.BASTO));
                cartas.add(new Carta(5, Palo.ORO));

                Mazo mazo = new Mazo("mazo_test", cartas);

                //when
                Carta carta = mazo.primeraCarta();

                //then
                Assertions.assertEquals(2, carta.getNumero());
                Assertions.assertEquals(Palo.BASTO, carta.getPalo());
            }
        }
    }

    @Nested
    @DisplayName("Given mazo sin cartas")
    class GivenMazoSinCartas {

        @Nested
        @DisplayName("when estaVacio")
        class WhenEstaVacio {

            @Test
            @DisplayName("then devolver true")
            void thenDevolverTrue() {

                //given
                Mazo mazo = new Mazo("mazo_test", new ArrayList<>());

                //when

                //then
                Assertions.assertTrue(mazo.estaVacio());
            }
        }
    }

    @Nested
    @DisplayName("Given un mazo")
    class GivenUnMazo2 {

        @Nested
        @DisplayName("When agregarCartas")
        class WhenAgregarCartas {

            @Test
            @DisplayName("Then se agregan las cartas al mazo")
            void thenSeAgreganLasCartasAlMazo() {

                //given
                Mazo mazo = new Mazo("mazo_test", new ArrayList<>());

                //when
                List<Carta> cartas = new ArrayList<>();
                cartas.add(new Carta(2, Palo.BASTO));
                cartas.add(new Carta(5, Palo.ORO));

                mazo.agregarCartas(cartas);

                //then
                Assertions.assertEquals(2, mazo.getCartas().size());

                // hace las verificaciones de las cartas de esta forma porque
                // cuando agrega las cartas las clona, son un objeto diferente al base
                Assertions.assertTrue(
                        mazo.getCartas().get(0).getNumero().equals(2)
                        || mazo.getCartas().get(0).getNumero().equals(5)
                        );
                Assertions.assertTrue(
                        mazo.getCartas().get(1).getNumero().equals(2)
                                || mazo.getCartas().get(1).getNumero().equals(5)
                );
                Assertions.assertTrue(
                        mazo.getCartas().get(0).getPalo().equals(Palo.BASTO)
                                || mazo.getCartas().get(0).getPalo().equals(Palo.ORO)
                );
                Assertions.assertTrue(
                        mazo.getCartas().get(1).getPalo().equals(Palo.BASTO)
                                || mazo.getCartas().get(1).getPalo().equals(Palo.ORO)
                );
            }
        }
    }

    @Nested
    @DisplayName("Given un mazo base")
    class GivenUnMazoBase {

        @Nested
        @DisplayName("When clonar")
        class WhenClonar {

            @Test
            @DisplayName("Then crear nuevo mazo con esas cartas")
            void thenCrearNuevoMazoConEsasCartas() {

                //given
                List<Carta> cartas = new ArrayList<>();
                cartas.add(new Carta(2, Palo.BASTO));
                cartas.add(new Carta(5, Palo.ORO));

                Mazo mazoBase = new Mazo("mazo_test", cartas);

                //when
                Mazo nuevoMazo = Mazo.clonar(mazoBase);

                //then
                Assertions.assertEquals(nuevoMazo.getCartas().size(), mazoBase.getCartas().size());

                // hace las verificaciones de las cartas de esta forma porque
                // cuando agrega las cartas las clona, son un objeto diferente al base
                Assertions.assertTrue(
                        nuevoMazo.getCartas().get(0).getNumero().equals(2)
                                || nuevoMazo.getCartas().get(0).getNumero().equals(5)
                );
                Assertions.assertTrue(
                        nuevoMazo.getCartas().get(1).getNumero().equals(2)
                                || nuevoMazo.getCartas().get(1).getNumero().equals(5)
                );
                Assertions.assertTrue(
                        nuevoMazo.getCartas().get(0).getPalo().equals(Palo.BASTO)
                                || nuevoMazo.getCartas().get(0).getPalo().equals(Palo.ORO)
                );
                Assertions.assertTrue(
                        nuevoMazo.getCartas().get(1).getPalo().equals(Palo.BASTO)
                                || nuevoMazo.getCartas().get(1).getPalo().equals(Palo.ORO)
                );
            }
        }
    }

    @Nested
    @DisplayName("Given un mazo de 4 cartas y 2 jugadores")
    class GivenUnMazoDe4CartasY2Jugadores {

        @Nested
        @DisplayName("When repartir mazo")
        class WhenRepartirMazo {

            @Test
            @DisplayName("Then devolver 2 submazos de 2 cartas")
            void thenDevolver2SubmazosDe2Cartas() {

                //given
                List<Carta> cartas = new ArrayList<>();
                cartas.add(new Carta(2, Palo.BASTO));
                cartas.add(new Carta(5, Palo.ORO));
                cartas.add(new Carta(8, Palo.ESPADA));
                cartas.add(new Carta(12, Palo.COPA));

                Mazo mazo = new Mazo("mazo_test", cartas);

                //when
                List<Mazo> mazos = mazo.repartir(2);

                //then
                Assertions.assertEquals(2, mazos.size());
                Assertions.assertEquals(2, mazos.get(0).getCartas().size());
                Assertions.assertEquals(2, mazos.get(1).getCartas().size());
            }
        }
    }
}
