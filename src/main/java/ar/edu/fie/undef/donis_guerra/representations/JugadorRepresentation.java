package ar.edu.fie.undef.donis_guerra.representations;

public class JugadorRepresentation {
    private String nombre;
    private boolean activo;

    public JugadorRepresentation(String nombre, boolean activo) {
        this.nombre = nombre;
        this.activo = activo;
    }

    public JugadorRepresentation(String nombre) {
        this.nombre = nombre;
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
}
