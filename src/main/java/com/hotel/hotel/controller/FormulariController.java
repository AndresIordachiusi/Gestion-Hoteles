package com.hotel.hotel.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import org.springframework.ui.Model;
import com.hotel.hotel.model.*;




@Controller
public class FormulariController {
    @GetMapping("/formulari")
    public String mostrarFormulari(Model model) {
        model.addAttribute("usuari", new UsuariDTO());
        return "formulari";
    }
    
    @PostMapping("/processar")
    public String processar(@ModelAttribute UsuariDTO usuari, Model model) {
        model.addAttribute("usuari", usuari);
        return "resultat";
    }
}
