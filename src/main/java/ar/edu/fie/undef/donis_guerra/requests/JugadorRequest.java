package ar.edu.fie.undef.donis_guerra.requests;

import ar.edu.fie.undef.donis_guerra.entities.Jugador;

public class JugadorRequest {
    private String nombre;

    public JugadorRequest(String nombre) {
        this.nombre = nombre;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Jugador construct(){
        return new Jugador(nombre);
    }
}
