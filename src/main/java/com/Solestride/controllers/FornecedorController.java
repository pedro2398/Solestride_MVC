package com.Solestride.controllers;

import com.Solestride.fornecedor.Fornecedor;
import com.Solestride.fornecedor.FornecedorRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("fornecedor")
public class FornecedorController {

    Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    FornecedorRepository repository;

    @GetMapping
    public ResponseEntity<List<Fornecedor>> get() {
        log.info("Exibindo todos os fornecedor!");
        return ResponseEntity.ok().body(repository.findAll());
    }

    @GetMapping("{id}")
    public ResponseEntity<Fornecedor> getById(@PathVariable Long id){
        log.info("Exibindo fornecedor de id: " + id);
        return ResponseEntity.ok(repository.findById(id).orElseThrow(() -> {
            return new RuntimeException("Id inexistenete");
        }));
    }

    @PostMapping
    public ResponseEntity<Fornecedor> post(@RequestBody Fornecedor entity) {
        log.info("Cadastrando fornecedor");
        return ResponseEntity.status(HttpStatus.CREATED).body(repository.save(entity));
    }

    @PutMapping("{id}")
    public ResponseEntity<Fornecedor> put(@PathVariable Long id, @RequestBody Fornecedor entity) {
        log.info("Alterando fornecedor com id: " + id);
        getById(id);
        entity.setId(id);
        repository.save(entity);
        return ResponseEntity.ok(entity);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Object> delete(@PathVariable Long id) {
        log.info("Deletando fornecedor com id: " + id);
        getById(id);
        repository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
