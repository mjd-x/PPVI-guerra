package ar.edu.fie.undef.donis_guerra.representations;

public class TurnoRepresentation {
    private String mensaje;
    private Integer id;
    private String identificacion;
    private Long jugadoresActivos;
    private String jugadorGanador;

    public TurnoRepresentation(String mensaje, Integer id, String identificacion, Long jugadoresActivos, String jugadorGanador) {
        this.mensaje = mensaje;
        this.id = id;
        this.identificacion = identificacion;
        this.jugadoresActivos = jugadoresActivos;
        this.jugadorGanador = jugadorGanador;
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

    public Long getJugadoresActivos() {
        return jugadoresActivos;
    }

    public void setJugadoresActivos(Long jugadoresActivos) {
        this.jugadoresActivos = jugadoresActivos;
    }
}
