package ar.edu.fie.undef.donis_guerra.entities;

import ar.edu.fie.undef.donis_guerra.representations.MazoRepresentation;
import com.google.common.collect.Lists;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Entity
public class Mazo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String identificacion;

    @OneToMany(cascade = CascadeType.PERSIST)  // un mazo tiene varias cartas
    @JoinColumn(name = "mazo_id")
    private List<Carta> cartas;

    public Mazo(String identificacion, List<Carta> cartas) {
        this.identificacion = identificacion;
        this.cartas = cartas;
    }

    public Mazo() {
    }

    public Mazo(List<Carta> cartas) {
        this.cartas = cartas;
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

    public List<Carta> getCartas() {
        return cartas;
    }

    public void setCartas(List<Carta> cartas) {
        this.cartas = cartas;
    }

    //*****************************************************
    // REPRESENTACIONES
    //*****************************************************

    /**
     * Representacion de un mazo
     * @return MazoRepresentation
     **/
    public MazoRepresentation representation() {
        return new MazoRepresentation(id, identificacion);
    }

    //*****************************************************
    // LOGICA
    //*****************************************************

    /**
     * Mezcla y reparte el mazo entre la cantidad de jugadores
     * @return lista con submazos de jugadores
     **/
    public List<Mazo> repartir(Integer numeroJugadores) {
        // mezcla las cartas
        Collections.shuffle(cartas);

        // cast a float y redondea por si no da entero el resultado
        float cantidad = cartas.size() / (float) numeroJugadores;

        // guava: partition Returns consecutive sublists of a list, each of the same size
        List<List<Carta>> listas = Lists.partition(cartas, Math.round(cantidad));

        // crea lista de submazos para repartir con las cartas
        List<Mazo> mazos = new ArrayList<>();
        for (List<Carta> submazo : listas) {
            Collections.shuffle(submazo);
            mazos.add(new Mazo("submazo_jugador", submazo));
        }
        return mazos;
    }

    /**
     * Saca y devuelve la primera carta del mazo
     * @return Carta
     **/
    public Carta primeraCarta() {
        return cartas.remove(0);
    }

    /**
     * Indica si esta vacio un mazo
     * @return boolean
     **/
    public boolean estaVacio() {
        return cartas.size() == 0;
    }

    /**
     * Agrega la lista de cartas al mazo
     * @return Mazo
     **/
    public Mazo agregarCartas(List<Carta> cartasAgregar) {
        // mezcla
        Collections.shuffle(cartasAgregar);

        // si agrega las mismas cartas, tienen los ids mas bajos
        // y las agrega al principio del mazo (porque ordena por id)
        // entonces las borrarlas y crea de vuelta
        // con ids mas altos asi las agrega al final

        List<Carta> cartasNuevas = Carta.clonar(cartasAgregar);
        cartas.addAll(cartasNuevas);

        return this;
    }

    /**
     * Clona un mazo existente
     * @return Mazo
     **/
    public static Mazo clonar(Mazo mazo) {
        return new Mazo("mazo_juego", Carta.clonar(mazo.cartas));
    }
}
