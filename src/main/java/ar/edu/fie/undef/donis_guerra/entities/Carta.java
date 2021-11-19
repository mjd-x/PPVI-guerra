package ar.edu.fie.undef.donis_guerra.entities;

import ar.edu.fie.undef.donis_guerra.entities.Palo;
import ar.edu.fie.undef.donis_guerra.representations.CartaRepresentation;

import javax.persistence.*;

@Entity
public class Carta {
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

    public CartaRepresentation representation() {
        return new CartaRepresentation(id, numero, palo);
    }
}
