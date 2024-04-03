package com.Solestride.fornecedor;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Optional;

@Controller
@RequestMapping("fornecedor")
public class FornecedorController {

    @Autowired
    FornecedorRepository repository;

    @GetMapping
    public String get(Model model) {

        model.addAttribute("fornecedor", repository.findAll());

        return "fornecedor/index";
    }

    @PostMapping
    public String post(@RequestBody @Valid Fornecedor entity, RedirectAttributes redirect) {

        repository.save(entity);
        redirect.addFlashAttribute("message", "Adicionado!");

        return "fornecedor/index";
    }

    @PutMapping("put/{id}")
    public String put(@PathVariable Long id, @RequestBody @Valid Fornecedor entity, RedirectAttributes redirect) {
        Optional<Fornecedor> fornecedor = repository.findById(id);

        if (fornecedor.isEmpty()) {
            redirect.addFlashAttribute("message", "Id não encontrado!");
            return "redirect:/fornecedor";
        }

        entity.setId(id);
        repository.save(entity);
        redirect.addFlashAttribute("message", "Salvo!");

        return "redirect:/fornecedor";
    }

    @DeleteMapping("delete/{id}")
    public String delete(@PathVariable Long id, RedirectAttributes redirect) {

        Optional<Fornecedor> fornecedor = repository.findById(id);

        if (fornecedor.isEmpty()) {
            redirect.addFlashAttribute("message", "Não foi possivel deletar");
            return "redirect:/fornecedor";
        }

        repository.deleteById(id);
        redirect.addFlashAttribute("message", "Deletado com sucesso!");

        return "redirect:/fornecedor";
    }
}