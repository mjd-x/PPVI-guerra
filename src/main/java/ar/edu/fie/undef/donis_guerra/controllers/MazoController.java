package ar.edu.fie.undef.donis_guerra.controllers;

import ar.edu.fie.undef.donis_guerra.representations.MazoRepresentation;
import ar.edu.fie.undef.donis_guerra.requests.MazoRequest;
import ar.edu.fie.undef.donis_guerra.services.MazoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MazoController {
    private final MazoService mazoService;

    public MazoController(MazoService mazoService) {
        this.mazoService = mazoService;
    }

    @PostMapping("mazos")
    private ResponseEntity<MazoRepresentation> create(@RequestBody MazoRequest mazo) {
        return ResponseEntity.ok(
            mazoService.create(mazo).representation()
        );
    }
}
