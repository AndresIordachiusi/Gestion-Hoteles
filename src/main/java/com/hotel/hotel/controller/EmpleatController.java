package com.hotel.hotel.controller;

import java.util.List;

import java.util.Optional;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.PathVariable;

import com.hotel.hotel.model.*;
import com.hotel.hotel.repository.EmpleatsRepository;
import com.hotel.hotel.repository.HotelRepository;
import com.hotel.hotel.repository.TascaRepository;

@Controller
public class EmpleatController {

private EmpleatsRepository empleatsRepository;
private TascaRepository tascaRepository;

public EmpleatController(EmpleatsRepository empleatsRepository, TascaRepository tascaRepository) {
    this.empleatsRepository = empleatsRepository;
    this.tascaRepository = tascaRepository;
}




@GetMapping("/empleats")
    public String showEmpleats(Model model) {
    List<Empleat> empleats = empleatsRepository.findAll();
    List<Tasca> tasques = tascaRepository.findAll();
    model.addAttribute("empleats", empleats);
    model.addAttribute("tasques", tasques);
    return "empleats/llistat"; 


    
}


}
