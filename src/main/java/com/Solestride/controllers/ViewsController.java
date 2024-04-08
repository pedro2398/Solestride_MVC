package com.Solestride.controllers;

import com.Solestride.fornecedor.FornecedorRepository;
import com.Solestride.requisicao.RequisicaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("dashboard")
public class ViewsController {

    @Autowired
    RequisicaoRepository requisicaoRepository;

    @Autowired
    FornecedorRepository fornecedorRepository;

    @GetMapping
    public String dashboard(Model model) {

        model.addAttribute("requisicoes", requisicaoRepository.findAll());

        return "dashboard/Index";
    }

    @GetMapping("requisicao/{id}")
    public String requisicao(Model model, @PathVariable Long id) {
        model.addAttribute("requisicao", requisicaoRepository.findById(id).orElseThrow(() -> {
            return new RuntimeException("Id inexistente!");
        }));

        return "requisicao/Index";
    }

    @GetMapping("fornecedor_requisicao/{id}")
    public String fornecedor(Model model, @PathVariable Long id) {
        model.addAttribute("fornecedor", fornecedorRepository.findById(id).orElseThrow(() -> {
            return new RuntimeException("Id inexistente!");
        }));

        return "fornecedor/Index";
    }
}
