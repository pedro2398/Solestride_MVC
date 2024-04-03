package com.Solestride.fabricante;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Optional;

@Controller
@RequestMapping("fabricante")
public class FabricanteController {

    @Autowired
    FabricanteRepository repository;

    @GetMapping
    public String get(Model model) {

        model.addAttribute("fabricante", repository.findAll());

        return "fabricante/index";
    }

    @PostMapping
    public String post(@RequestBody @Valid Fabricante entity, RedirectAttributes redirect) {

        repository.save(entity);
        redirect.addFlashAttribute("message", "Adicionado!");

        return "fabricante/index";
    }

    @PutMapping("put/{id}")
    public String put(@PathVariable Long id, @RequestBody @Valid Fabricante entity, RedirectAttributes redirect) {
        Optional<Fabricante> fabricante = repository.findById(id);

        if (fabricante.isEmpty()) {
            redirect.addFlashAttribute("message", "Id não encontrado!");
            return "redirect:/fabricante";
        }

        entity.setId(id);
        repository.save(entity);
        redirect.addFlashAttribute("message", "Salvo!");

        return "redirect:/fabricante";
    }

    @DeleteMapping("delete/{id}")
    public String delete(@PathVariable Long id, RedirectAttributes redirect) {

        Optional<Fabricante> fabricante = repository.findById(id);

        if (fabricante.isEmpty()) {
            redirect.addFlashAttribute("message", "Não foi possivel deletar");
            return "redirect:/fabricante";
        }

        repository.deleteById(id);
        redirect.addFlashAttribute("message", "Deletado com sucesso!");

        return "redirect:/fabricante";
    }
}