package com.hotel.hotel.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import org.springframework.ui.Model;
import com.hotel.hotel.model.*;
import com.hotel.hotel.repository.EmpleatsRepository;




@Controller
public class FormulariController {

    private EmpleatsRepository empleatsRepository;

    

    public FormulariController(EmpleatsRepository empleatsRepository) {
        this.empleatsRepository = empleatsRepository;
    }

    @GetMapping("/formulari")
    public String mostrarFormulari(Model model) {
        model.addAttribute("usuari", new UsuariDTO());
        return "FORMULARIS/formulari";
    }
    
    @PostMapping("/processar")
    public String processar(@ModelAttribute UsuariDTO usuari, Model model) {
        model.addAttribute("usuari", usuari);
        return "FORMULARIS/resultat";
    }


    @GetMapping("/altaempleat")
    public String altaempleat(Model model) {
        model.addAttribute("empleat", new Empleat());
        return "FORMULARIS/altaempleat";
    }

    @PostMapping("/registrarEmpleat")
    public String registreEmpleat(Empleat empleat) {
        empleatsRepository.save(empleat);

        return "redirect:/empleats/llistarEmpleats";
    }




}
