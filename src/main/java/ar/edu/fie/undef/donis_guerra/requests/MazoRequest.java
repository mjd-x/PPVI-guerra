package ar.edu.fie.undef.donis_guerra.requests;

import ar.edu.fie.undef.donis_guerra.entities.Mazo;

import java.util.List;
import java.util.stream.Collectors;

public class MazoRequest {
    private String identificacion;
    private List<CartaRequest> cartas;

    public MazoRequest(String identificacion, List<CartaRequest> cartas) {
        this.identificacion = identificacion;
        this.cartas = cartas;
    }

    public String getIdentificacion() {
        return identificacion;
    }

    public void setIdentificacion(String identificacion) {
        this.identificacion = identificacion;
    }

    public List<CartaRequest> getCartas() {
        return cartas;
    }

    public void setCartas(List<CartaRequest> cartas) {
        this.cartas = cartas;
    }

    public Mazo construct() {
        return new Mazo(
            identificacion,
            cartas.stream().map(CartaRequest::construct).collect(Collectors.toList())
        );
    }
}
