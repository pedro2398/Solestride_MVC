package com.Solestride.controllers;

import com.Solestride.fabricante.Fabricante;
import com.Solestride.fabricante.FabricanteRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("fabricante")
public class FabricanteController {

    Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    FabricanteRepository repository;

    @GetMapping
    public ResponseEntity<List<Fabricante>> get() {
        log.info("Exibindo todos os fabricantes!");
        return ResponseEntity.ok().body(repository.findAll());
    }

    @GetMapping("{id}")
    public ResponseEntity<Fabricante> getById(@PathVariable Long id){
        log.info("Exibindo fabricante de id: " + id);
        return ResponseEntity.ok(repository.findById(id).orElseThrow(() -> {
            return new RuntimeException("Id inexistenete");
        }));
    }

    @PostMapping
    public ResponseEntity<Fabricante> post(@RequestBody Fabricante entity) {
        log.info("Cadastrando fabricante");
        return ResponseEntity.status(HttpStatus.CREATED).body(repository.save(entity));
    }

    @PutMapping("{id}")
    public ResponseEntity<Fabricante> put(@PathVariable Long id, @RequestBody Fabricante entity) {
        log.info("Alterando fabricante com id: " + id);
        getById(id);
        entity.setId(id);
        repository.save(entity);
        return ResponseEntity.ok(entity);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Object> delete(@PathVariable Long id) {
        log.info("Deletando fabricante com id: " + id);
        getById(id);
        repository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
