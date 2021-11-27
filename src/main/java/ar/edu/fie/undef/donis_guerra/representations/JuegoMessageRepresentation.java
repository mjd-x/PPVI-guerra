package ar.edu.fie.undef.donis_guerra.representations;

public class JuegoMessageRepresentation {
    private String mensaje;
    private Integer id;
    private String identificacion;

    public JuegoMessageRepresentation(String mensaje, Integer id, String identificacion) {
        this.mensaje = mensaje;
        this.id = id;
        this.identificacion = identificacion;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getIdentificacion() {
        return identificacion;
    }

    public void setIdentificacion(String identificacion) {
        this.identificacion = identificacion;
    }
}
