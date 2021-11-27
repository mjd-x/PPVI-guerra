package ar.edu.fie.undef.donis_guerra.representations;

public class JugadorCartasRepresentation {
    private Integer id;
    private String nombre;
    private Integer cantidadCartas;

    public JugadorCartasRepresentation(Integer id, String nombre, Integer cantidadCartas) {
        this.id = id;
        this.nombre = nombre;
        this.cantidadCartas = cantidadCartas;
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

    public Integer getCantidadCartas() {
        return cantidadCartas;
    }

    public void setCantidadCartas(Integer cantidadCartas) {
        this.cantidadCartas = cantidadCartas;
    }
}
