package ar.edu.fie.undef.donis_guerra.representations;

import ar.edu.fie.undef.donis_guerra.entities.Palo;

public class CartaRepresentation {
    private Integer numero;
    private Palo palo;

    public CartaRepresentation(Integer numero, Palo palo) {
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
}
