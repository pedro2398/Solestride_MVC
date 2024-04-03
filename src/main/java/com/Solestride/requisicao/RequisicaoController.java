package com.Solestride.requisicao;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Optional;

@Controller
@RequestMapping("requisicao")
public class RequisicaoController {

    @Autowired
    RequisicaoRepository repository;

    @GetMapping
    public String get(Model model) {

        model.addAttribute("requisicao", repository.findAll());

        return "requisicao/index";
    }

    @PostMapping
    public String post(@RequestBody @Valid Requisicao entity, RedirectAttributes redirect) {

        repository.save(entity);
        redirect.addFlashAttribute("message", "Adicionado!");

        return "requisicao/index";
    }

    @PutMapping("put/{id}")
    public String put(@PathVariable Long id, @RequestBody @Valid Requisicao entity, RedirectAttributes redirect) {
        Optional<Requisicao> requisicao = repository.findById(id);

        if (requisicao.isEmpty()) {
            redirect.addFlashAttribute("message", "Id não encontrado!");
            return "redirect:/requisicao";
        }

        entity.setId(id);
        repository.save(entity);
        redirect.addFlashAttribute("message", "Salvo!");

        return "redirect:/requisicao";
    }

    @DeleteMapping("delete/{id}")
    public String delete(@PathVariable Long id, RedirectAttributes redirect) {

        Optional<Requisicao> requisicao = repository.findById(id);

        if (requisicao.isEmpty()) {
            redirect.addFlashAttribute("message", "Não foi possivel deletar");
            return "redirect:/requisicao";
        }

        repository.deleteById(id);
        redirect.addFlashAttribute("message", "Deletado com sucesso!");

        return "redirect:/requisicao";
    }
}
