package ar.edu.fie.undef.donis_guerra.representations;

import ar.edu.fie.undef.donis_guerra.entities.Palo;

public class CartaRepresentation {
    private Integer id;
    private Integer numero;
    private Palo palo;

    public CartaRepresentation(Integer id, Integer numero, Palo palo) {
        this.id = id;
        this.numero = numero;
        this.palo = palo;
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

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
