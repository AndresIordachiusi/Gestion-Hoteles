package com.hotel.hotel.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.hotel.hotel.model.*;
import com.hotel.hotel.repository.ClientsRepository;
import com.hotel.hotel.repository.HabitacioRepository;
import com.hotel.hotel.repository.ReservaRepository;

@Controller
public class ReservesController {
    
 
private ReservaRepository reservaRepository;

    private ClientsRepository clientsRepository;
    private HabitacioRepository habitacioRepository;

   public ReservesController(ReservaRepository reservaRepository, ClientsRepository clientsRepository, HabitacioRepository habitacioRepository) {
       this.reservaRepository = reservaRepository;
       this.clientsRepository = clientsRepository;
       this.habitacioRepository = habitacioRepository;

   }


   @GetMapping("/admin/reserves")
   public String showReserves(Model model) {
       List<Reserva> reserves = reservaRepository.findAll();
       List<Client> clients = clientsRepository.findAll();
       List<Habitacio> habitacions = habitacioRepository.findAll();
       model.addAttribute("reserves", reserves);
       model.addAttribute("clients", clients);
       model.addAttribute("habitacions", habitacions);
       return "reserves/reserves";
   }






  
}

