package ar.edu.fie.undef.donis_guerra.initializers;

import ar.edu.fie.undef.donis_guerra.entities.Carta;
import ar.edu.fie.undef.donis_guerra.entities.Mazo;
import ar.edu.fie.undef.donis_guerra.entities.Palo;
import ar.edu.fie.undef.donis_guerra.services.MazoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class MazoInitializer implements InitializingBean {
    private final MazoService mazoService;
    private final Logger logger = LoggerFactory.getLogger(MazoInitializer.class);

    public MazoInitializer(MazoService mazoService) {
        this.mazoService = mazoService;
    }

    @Override
    public void afterPropertiesSet() {

        if (mazoService.count() == 0) {
            List<Carta> cartas = new ArrayList<>();

            for (int i=1 ; i < 13 ; i++) {
//                for (Palo palo : Palo.values()) {
//                    cartas.add(new Carta(i, palo));
//                }
                cartas.add(new Carta(i, Palo.BASTO));
            }

            Mazo mazo = new Mazo("mazo_base", cartas);
            mazoService.save(mazo);

        } else {
            logger.info("Ya se inicializo el mazo anteriormente");
        }
    }
}
