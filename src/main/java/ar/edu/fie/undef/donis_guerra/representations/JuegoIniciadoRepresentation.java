package ar.edu.fie.undef.donis_guerra.representations;

public class JuegoIniciadoRepresentation {
    private String message;
    private Integer id;
    private String identificacion;

    public JuegoIniciadoRepresentation(String message, Integer id, String identificacion) {
        this.message = message;
        this.id = id;
        this.identificacion = identificacion;
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

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
