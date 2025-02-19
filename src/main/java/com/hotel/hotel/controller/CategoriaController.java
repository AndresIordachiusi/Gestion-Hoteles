package com.hotel.hotel.controller;


import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.hotel.hotel.model.Categoria;
import com.hotel.hotel.repository.CategoriaRepository;

@Controller
public class CategoriaController {

    private final CategoriaRepository categoriaRepository;

    public CategoriaController(CategoriaRepository categoriaRepository) {
        this.categoriaRepository = categoriaRepository;
    }

    @GetMapping("/categories")
    public String showCategories(Model model) {
        List<Categoria> categories = categoriaRepository.findAll();
        model.addAttribute("categories", categories);
        return "categoria/llistat"; 
    }
}
