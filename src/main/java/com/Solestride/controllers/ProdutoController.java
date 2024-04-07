package com.Solestride.controllers;

import com.Solestride.produto.Produto;
import com.Solestride.produto.ProdutoRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("produto")
public class ProdutoController {

    Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    ProdutoRepository repository;

    @GetMapping
    public ResponseEntity<List<Produto>> get() {
        log.info("Exibindo todos os produtos!");
        return ResponseEntity.ok().body(repository.findAll());
    }

    @GetMapping("{id}")
    public ResponseEntity<Produto> getById(@PathVariable Long id){
        log.info("Exibindo produto de id: " + id);
        return ResponseEntity.ok(repository.findById(id).orElseThrow(() -> {
            return new RuntimeException("Id inexistenete");
        }));
    }

    @PostMapping
    public ResponseEntity<Produto> post(@RequestBody Produto entity) {
        log.info("Cadastrando produto");
        return ResponseEntity.status(HttpStatus.CREATED).body(repository.save(entity));
    }

    @PutMapping("{id}")
    public ResponseEntity<Produto> put(@PathVariable Long id, @RequestBody Produto entity) {
        log.info("Alterando produto com id: " + id);
        getById(id);
        entity.setId(id);
        repository.save(entity);
        return ResponseEntity.ok(entity);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Object> delete(@PathVariable Long id) {
        log.info("Deletando produto com id: " + id);
        getById(id);
        repository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
