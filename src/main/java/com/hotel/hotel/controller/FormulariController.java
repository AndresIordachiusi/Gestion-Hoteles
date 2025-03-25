package com.hotel.hotel.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import org.springframework.ui.Model;
import com.hotel.hotel.model.*;
import com.hotel.hotel.repository.EmpleatsRepository;
import com.hotel.hotel.repository.TascaRepository;



@Controller
public class FormulariController {

    private EmpleatsRepository empleatsRepository;
    private TascaRepository tascaRepository;
    

    public FormulariController(EmpleatsRepository empleatsRepository, TascaRepository tascaRepository) {
        this.empleatsRepository = empleatsRepository;
        this.tascaRepository = tascaRepository;
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
    @GetMapping("/empleats/llistat")
    public String llistatEmpleats(Model model) {
        model.addAttribute("empleats", empleatsRepository.findAll());
        return "empleats/llistat";
    }
 

    @GetMapping("/altaempleat")
    public String altaempleat(Model model) {
        List<Tasca> tasques = tascaRepository.findAll();
        model.addAttribute("tasques", tasques);
        model.addAttribute("empleat", new Empleat());
        return "FORMULARIS/altaempleat";
    }

    @PostMapping("/registrarEmpleat")
    public String registreEmpleat(Empleat empleat) {
        empleatsRepository.save(empleat);

        return "redirect:/empleats/llistat";
    }




}
