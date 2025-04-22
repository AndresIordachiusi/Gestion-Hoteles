package com.hotel.hotel.controller;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.hotel.hotel.model.*;
import com.hotel.hotel.repository.ClientsRepository;
import com.hotel.hotel.repository.HabitacioRepository;
import com.hotel.hotel.repository.HotelRepository;
import com.hotel.hotel.repository.ReservaRepository;
import com.hotel.hotel.repository.UsuariRepository;

import jakarta.servlet.http.HttpSession;
import jakarta.transaction.Transactional;

@Controller
public class ReservesController {
    
 
private ReservaRepository reservaRepository;

    private ClientsRepository clientsRepository;
    private HabitacioRepository habitacioRepository;
    private UsuariRepository usuariRepository;
    private HotelRepository hotelRepository;

   public ReservesController(ReservaRepository reservaRepository, ClientsRepository clientsRepository, HabitacioRepository habitacioRepository, UsuariRepository usuariRepository, HotelRepository hotelRepository) {   
       this.reservaRepository = reservaRepository;
       this.clientsRepository = clientsRepository;
       this.habitacioRepository = habitacioRepository;
       this.usuariRepository = usuariRepository;
       this.hotelRepository = hotelRepository;
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



   @GetMapping("/hotels/{id}/reserves")
   public String reserva(@PathVariable Long id, Model model, HttpSession session){

       Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
         String username = authentication.getName();



        Optional<Usuari> usuario = usuariRepository.findByEmail(username);

        if (usuario.isPresent()) {
            model.addAttribute("nom", usuario.get().getPersona().getNom());
            model.addAttribute("cognom", usuario.get().getPersona().getCognom());
            
            Optional<Hotel> opt = this.hotelRepository.findById(id);

            if (opt.isPresent()) {
                session.setAttribute("id_hotel", id);
                Hotel h = opt.get();
                model.addAttribute("hotel", h);

                List<Habitacio> habitacions = habitacioRepository.findByHotelIdAndEstat(id, EstatHabitacio.DISPONIBLE);
                model.addAttribute("habitacions", habitacions);
            }

            Client client = (Client) usuario.get().getPersona();
            
            Reserva reserva = new Reserva();
            reserva.setClient(client);
            model.addAttribute("reserva", reserva);
            model.addAttribute("client", client);


        return "FORMULARIS/Reserva";


        }

    return "redirect:/login?error";

   }



    @PostMapping("/reservahabitacio")
   public String reservarHabitacio(Model model, @ModelAttribute Reserva reserva, BindingResult result ){


            reserva.setData_reserva(Date.valueOf(LocalDate.now()));
            
            Long habitacioId = reserva.getHabitacio().getId();
            Optional<Habitacio> habitacioOpt = habitacioRepository.findById(habitacioId);
            
            
            if (habitacioOpt.isPresent()) {
                Habitacio habitacio = habitacioOpt.get();

                habitacio.setEstat(EstatHabitacio.OCUPADA);
                reserva.setHabitacio(habitacio);
                reservaRepository.save(reserva);
                habitacioRepository.save(habitacio);
            }
            
        return "redirect:/";
   }


  
}

