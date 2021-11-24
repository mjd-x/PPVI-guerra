package ar.edu.fie.undef.donis_guerra.representations;

public class JugadorRepresentation {
    private Integer id;
    private String nombre;

    public JugadorRepresentation(Integer id, String nombre) {
        this.id = id;
        this.nombre = nombre;
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
}
