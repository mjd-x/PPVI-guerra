package ar.edu.fie.undef.donis_guerra.repositories;

import ar.edu.fie.undef.donis_guerra.entities.Mazo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MazoRepository extends JpaRepository<Mazo, Integer> {
    Integer countAllBy();
}
