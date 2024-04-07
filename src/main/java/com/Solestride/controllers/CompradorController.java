package com.Solestride.controllers;

import com.Solestride.comprador.Comprador;
import com.Solestride.comprador.CompradorRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("comprador")
public class CompradorController {

    Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    CompradorRepository repository;

    @GetMapping
    public ResponseEntity<List<Comprador>> get() {
        log.info("Exibindo todos os compradores!");
        return ResponseEntity.ok().body(repository.findAll());
    }

    @GetMapping("{id}")
    public ResponseEntity<Comprador> getById(@PathVariable Long id){
        log.info("Exibindo comprador de id: " + id);
        return ResponseEntity.ok(repository.findById(id).orElseThrow(() -> {
            return new RuntimeException("Id inexistenete");
        }));
    }

    @PostMapping
    public ResponseEntity<Comprador> post(@RequestBody Comprador entity) {
        log.info("Cadastrando comprador");
        return ResponseEntity.status(HttpStatus.CREATED).body(repository.save(entity));
    }

    @PutMapping("{id}")
    public ResponseEntity<Comprador> put(@PathVariable Long id, @RequestBody Comprador entity) {
        log.info("Alterando comprador com id: " + id);
        getById(id);
        entity.setId(id);
        repository.save(entity);
        return ResponseEntity.ok(entity);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Object> delete(@PathVariable Long id) {
        log.info("Deletando comprador com id: " + id);
        getById(id);
        repository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
