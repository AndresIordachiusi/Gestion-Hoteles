package com.hotel.hotel.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;

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


    @GetMapping("/tasques")
    public String showTasques(Model model) {
    List<Empleat> empleats = empleatsRepository.findAll();
    List<Tasca> tasques = tascaRepository.findAll();
    model.addAttribute("empleats", empleats);  
    model.addAttribute("tasques", tasques);
    return "empleats/tasques";
    }


}
