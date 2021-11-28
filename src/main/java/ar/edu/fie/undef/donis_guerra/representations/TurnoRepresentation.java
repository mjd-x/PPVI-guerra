package ar.edu.fie.undef.donis_guerra.representations;

public class TurnoRepresentation {
    private String mensaje;
    private String jugadorGanador;
    private Integer id;
    private String identificacion;

    public TurnoRepresentation(String mensaje, String jugadorGanador, Integer id, String identificacion) {
        this.mensaje = mensaje;
        this.jugadorGanador = jugadorGanador;
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

    public String getJugadorGanador() {
        return jugadorGanador;
    }

    public void setJugadorGanador(String jugadorGanador) {
        this.jugadorGanador = jugadorGanador;
    }
}
