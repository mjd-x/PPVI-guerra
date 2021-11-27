package ar.edu.fie.undef.donis_guerra.entities;

import ar.edu.fie.undef.donis_guerra.representations.CartaRepresentation;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Entity
public class Carta {
    // Cartas con un numero y palo que conforman un mazo

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private Integer numero;

    @Enumerated(EnumType.STRING)
    private Palo palo;

    public Carta(Integer numero, Palo palo) {
        this.numero = numero;
        this.palo = palo;
    }

    public Carta(){}


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getNumero() {
        return numero;
    }

    public void setNumero(Integer numero) {
        this.numero = numero;
    }

    public Palo getPalo() {
        return palo;
    }

    public void setPalo(Palo palo) {
        this.palo = palo;
    }

    //*****************************************************
    // REPRESENTACIONES
    //*****************************************************

    /**
     * Representacion de una carta
     * @return CartaRepresentation
     **/
    public CartaRepresentation representation() {
        return new CartaRepresentation(id, numero, palo);
    }

    //*****************************************************
    // LOGICA
    //*****************************************************

    /**
     * Clona una lista de cartas
     * @return lista clonada de cartas
     **/
    public static List<Carta> clonar(List<Carta> cartas) {
        // Utilizado al clonar un mazo

        Collections.shuffle(cartas);
        List<Carta> cartas_clone = new ArrayList<>();

        for (Carta carta : cartas) {
            cartas_clone.add(new Carta(carta.numero, carta.palo));
        }

        return cartas_clone;
    }
}
