package com.Solestride.produto;

import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("produto")
public class ProdutoController {

    @Autowired
    ProdutoRepository repository;

    @GetMapping
    public String get(Model model) {

        model.addAttribute("produto", repository.findAll());

        return "produto/index";
    }

    @PostMapping
    public String post(@RequestBody @Valid Produto entity, RedirectAttributes redirect) {

        repository.save(entity);
        redirect.addFlashAttribute("message", "Adicionado!");

        return "produto/index";
    }

    @PutMapping("put/{id}")
    public String put(@PathVariable Long id, @RequestBody @Valid Produto entity, RedirectAttributes redirect) {
        Optional<Produto> produto = repository.findById(id);

        if (produto.isEmpty()) {
            redirect.addFlashAttribute("message", "Id não encontrado!");
            return "redirect:/produto";
        }

        entity.setId(id);
        repository.save(entity);
        redirect.addFlashAttribute("message", "Salvo!");

        return "redirect:/produto";
    }

    @DeleteMapping("delete/{id}")
    public String delete(@PathVariable Long id, RedirectAttributes redirect) {

        Optional<Produto> produto = repository.findById(id);

        if (produto.isEmpty()) {
            redirect.addFlashAttribute("message", "Não foi possivel deletar");
            return "redirect:/produto";
        }

        repository.deleteById(id);
        redirect.addFlashAttribute("message", "Deletado com sucesso!");

        return "redirect:/produto";
    }
}