package ar.edu.fie.undef.donis_guerra.representations;

public class MazoRepresentation {
    private Integer id;
    private String identificacion;


//    public MazoRepresentation(String identificacion) {
//        this.identificacion = identificacion;
//    }

    public MazoRepresentation(Integer id, String identificacion) {
        this.id = id;
        this.identificacion = identificacion;
    }

    public String getIdentificacion() {
        return identificacion;
    }

    public void setIdentificacion(String identificacion) {
        this.identificacion = identificacion;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
