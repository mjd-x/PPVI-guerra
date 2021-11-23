package ar.edu.fie.undef.donis_guerra.entities;

import ar.edu.fie.undef.donis_guerra.representations.JugadorRepresentation;

import javax.persistence.*;

@Entity
public class Jugador {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String nombre;
    private boolean activo;

    @OneToOne(cascade = CascadeType.PERSIST)  // un jugador tiene un sub-mazo
    @JoinColumn(name = "jugador_id")
    private Mazo mazo;  // sub-mazo de cada JUGADOR, parte del mazo que era del juego inicialmente


    public Jugador(String nombre, boolean activo, Mazo mazo) {
        this.nombre = nombre;
        this.activo = activo;
        this.mazo = mazo;
    }

    public Jugador() {
    }

    public Jugador(String nombre) {
        this.nombre = nombre;
        this.activo = false;
        this.mazo = null;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public boolean isActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }

    public Mazo getMazo() {
        return mazo;
    }

    public void setMazo(Mazo mazo) {
        this.mazo = mazo;
    }

    public JugadorRepresentation representation() {
        return new JugadorRepresentation(id, nombre);
    }
}
