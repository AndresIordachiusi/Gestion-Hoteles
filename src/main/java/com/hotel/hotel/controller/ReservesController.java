package com.hotel.hotel.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.hotel.hotel.model.*;
import com.hotel.hotel.repository.ReservaRepository;

@Controller
public class ReservesController {
    
 
private ReservaRepository reservaRepository;

   public ReservesController(ReservaRepository reservaRepository) {
       this.reservaRepository = reservaRepository;
   }


   @GetMapping("/reserves")
   public String showReserves(Model model) {
       List<Reserva> reserves = reservaRepository.findAll();
       model.addAttribute("reserves", reserves);
       return "reserves/reserves";
   }






  
}

