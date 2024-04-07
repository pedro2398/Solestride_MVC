package com.Solestride.controllers;

import com.Solestride.requisicao.Requisicao;
import com.Solestride.requisicao.RequisicaoRepository;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("requisicao")
public class RequisicaoController {

    Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    RequisicaoRepository repository;

    @GetMapping
    public ResponseEntity<List<Requisicao>> get() {
        log.info("Exibindo todos as requisições!");
        return ResponseEntity.ok().body(repository.findAll());
    }

    @GetMapping("{id}")
    public ResponseEntity<Requisicao> getById(@PathVariable Long id){
        log.info("Exibindo requisição de id: " + id);
        return ResponseEntity.ok().body(repository.findById(id).orElseThrow(() -> {
            return new RuntimeException("Id inexistente!");
        }));
    }

    @PostMapping
    public ResponseEntity<Requisicao> post(@RequestBody Requisicao entity) {
        log.info("Cadastrando requisição");
        entity.setValorTotal(entity.getProduto().getValor().multiply(entity.getQuantidade()));
        return ResponseEntity.status(HttpStatus.CREATED).body(repository.save(entity));
    }

    @PutMapping("{id}")
    public ResponseEntity<Requisicao> put(@PathVariable Long id, @RequestBody @Valid Requisicao entity) {
        log.info("Alterando requisição com id: " + id);
        getById(id);
        entity.setId(id);
        entity.setValorTotal(entity.getProduto().getValor().multiply(entity.getQuantidade()));
        repository.save(entity);
        return ResponseEntity.ok(repository.save( entity));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Object> delete(@PathVariable Long id) {
        log.info("Deletando requisição com id: " + id);
        repository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}