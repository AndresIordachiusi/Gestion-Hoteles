package com.hotel.hotel.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.hotel.hotel.model.Empleat;
import com.hotel.hotel.model.Tasca;
import com.hotel.hotel.repository.EmpleatsRepository;
import com.hotel.hotel.repository.TascaRepository;
import com.hotel.hotel.model.*;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

@Controller
public class TasquesController {
    
    private EmpleatsRepository empleatsRepository;
    private TascaRepository tascaRepository;

    public TasquesController(EmpleatsRepository empleatsRepository, TascaRepository tascaRepository) {
        this.empleatsRepository = empleatsRepository;
        this.tascaRepository = tascaRepository;
    }


    @GetMapping("/empleats/tasques")
    public String showTasques(Model model) {
    List<Empleat> empleats = empleatsRepository.findAll();
    List<Tasca> tasques = tascaRepository.findAll();
    model.addAttribute("empleats", empleats);  
    model.addAttribute("tasques", tasques);
    return "empleats/tasques";
    }


    @PostMapping("/empleats/asignartasca")
    public String assignarTasca(@RequestParam Long empleatId, @RequestParam Long tascaId) {
        Empleat empleat = empleatsRepository.findById(empleatId).orElse(null);
        Tasca tasca = tascaRepository.findById(tascaId).orElse(null);
            
            empleat.getTasques().add(tasca);
            tasca.getEmpleats().add(empleat);
            empleatsRepository.save(empleat);
            tascaRepository.save(tasca);
        
        return "redirect:/empleats/tasques";
    }

}
