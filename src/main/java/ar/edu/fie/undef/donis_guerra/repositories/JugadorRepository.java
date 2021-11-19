package ar.edu.fie.undef.donis_guerra.repositories;

import ar.edu.fie.undef.donis_guerra.entities.Jugador;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface JugadorRepository extends JpaRepository<Jugador, Integer> {
    List<Jugador> findJugadorByActivo(boolean activo);
}
