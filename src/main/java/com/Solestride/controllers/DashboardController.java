package com.Solestride.controllers;

import com.Solestride.produto.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("dashboard")
public class DashboardController {

    @Autowired
    ProdutoRepository produtoRepository;

    @GetMapping
    public String dashboard(Model model) {

        model.addAttribute("produtos", produtoRepository.findAll());

        return "dashboard/Index";
    }
}
