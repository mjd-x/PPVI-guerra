package ar.edu.fie.undef.donis_guerra.requests;

import ar.edu.fie.undef.donis_guerra.entities.Jugador;
import ar.edu.fie.undef.donis_guerra.entities.Mazo;

public class JugadorRequest {
    private String nombre;
    private boolean activo;
    private Mazo mazo;

    public JugadorRequest(String nombre, boolean activo, Mazo mazo) {
        this.nombre = nombre;
        this.activo = activo;
        this.mazo = mazo;
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

    public Jugador construct(){
        return new Jugador(nombre, activo, mazo);
    }
}
