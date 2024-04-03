package com.Solestride.comprador;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Optional;

@Controller
@RequestMapping("comprador")
public class CompradorController {

    @Autowired
    CompradorRepository repository;

    @GetMapping
    public String get(Model model) {

        model.addAttribute("comprador", repository.findAll());

        return "comprador/Index ";
    }

    @PostMapping
    public String post(@RequestBody @Valid Comprador entity, RedirectAttributes redirect) {

        repository.save(entity);
        redirect.addFlashAttribute("message", "Adicionado!");

        return "comprador/index";
    }

    @PutMapping("put/{id}")
    public String put(@PathVariable Long id, @RequestBody @Valid Comprador entity, RedirectAttributes redirect) {
        Optional<Comprador> comprador = repository.findById(id);

        if(comprador.isEmpty()) {
            redirect.addFlashAttribute("message", "Id não encontrado!");
            return "redirect:/comprador";
        }

        entity.setId(id);
        repository.save(entity);
        redirect.addFlashAttribute("message","Salvo!");

        return "redirect:/comprador";
    }

    @DeleteMapping("delete/{id}")
    public String delete(@PathVariable Long id, RedirectAttributes redirect) {

        Optional<Comprador> comprador = repository.findById(id);

        if(comprador.isEmpty()) {
            redirect.addFlashAttribute("message", "Não foi possivel deletar");
            return "redirect:/comprador";
        }

        repository.deleteById(id);
        redirect.addFlashAttribute("message", "Deletado com sucesso!");

        return "redirect:/comprador";
    }
}