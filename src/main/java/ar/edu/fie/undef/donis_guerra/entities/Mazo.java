package ar.edu.fie.undef.donis_guerra.entities;

import ar.edu.fie.undef.donis_guerra.representations.MazoRepresentation;

import javax.persistence.*;
import java.util.List;

@Entity
public class Mazo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String identificacion;

    @OneToMany(cascade = CascadeType.PERSIST)  // un mazo tiene varias cartas
    @JoinColumn(name = "mazo_id")
    private List<Carta> cartas;

    public Mazo(String identificacion, List<Carta> cartas) {
        this.identificacion = identificacion;
        this.cartas = cartas;
    }

    public Mazo() {
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

    public List<Carta> getCartas() {
        return cartas;
    }

    public void setCartas(List<Carta> cartas) {
        this.cartas = cartas;
    }

    public MazoRepresentation representation() {
        return new MazoRepresentation(identificacion);
    }

}
