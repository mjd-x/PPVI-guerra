package ar.edu.fie.undef.donis_guerra.services;

import ar.edu.fie.undef.donis_guerra.entities.Mazo;
import ar.edu.fie.undef.donis_guerra.repositories.MazoRepository;
import ar.edu.fie.undef.donis_guerra.requests.MazoRequest;
import org.springframework.stereotype.Service;

@Service
public class MazoServiceImpl implements MazoService {
    private final MazoRepository mazoRepository;


    public MazoServiceImpl(MazoRepository mazoRepository) {
        this.mazoRepository = mazoRepository;
    }

    @Override
    public Mazo create(MazoRequest mazo) {
        return mazoRepository.save(mazo.construct());
    }

    @Override
    public Mazo findById(Integer mazoId) {
        return mazoRepository.findById(mazoId).get();
    }
}
