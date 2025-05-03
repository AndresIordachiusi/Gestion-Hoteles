package com.hotel.hotel.controller;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.hotel.hotel.model.*;
import com.hotel.hotel.repository.*;


import jakarta.servlet.http.HttpSession;

@Controller
public class AdminController {

    private HabitacioRepository habitacioRepository;
    private UsuariRepository usuariRepository;
    private HotelRepository hotelRepository;
    private FacturaRepository facturaRepository;
    private ReservaRepository reservaRepository;
    private ClientsRepository clientsRepository;

   public AdminController(ReservaRepository reservaRepository, HabitacioRepository habitacioRepository, UsuariRepository usuariRepository, HotelRepository hotelRepository, FacturaRepository facturaRepository, ClientsRepository clientsRepository) {   
       this.reservaRepository = reservaRepository;
       this.habitacioRepository = habitacioRepository;
       this.usuariRepository = usuariRepository;
       this.hotelRepository = hotelRepository;
       this.facturaRepository = facturaRepository;
       this.clientsRepository = clientsRepository;
   }

   @GetMapping("admin/dashboard")
   public String dashboard(Model model,  HttpSession session) {
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

 // Obtenim el nom d'usuari (email en el nostre cas!)
 String username = authentication.getName();

 System.out.println("Usuari: " + username);

 Optional<Usuari> usuari = usuariRepository.findByEmail(username);

 if (usuari.isPresent()) {
    model.addAttribute("email", usuari.get().getEmail());
    model.addAttribute("rol", usuari.get().getRol());
 }

    session.setAttribute("email", usuari.get().getEmail());
    session.setAttribute("rol", usuari.get().getRol());

       
       return "admin/dashboard"; 
   }


   @GetMapping("/admin/reserves")
   public String showReserves(Model model) {
       List<Reserva> reserves = reservaRepository.findAll();
       model.addAttribute("reserves", reserves);
       return "admin/reserves";
   }



   @GetMapping("/admin/facturas/facturas/{id}")
   public String llistarFactures(Model model, HttpSession session, @PathVariable Long id) {

       List <Factura> factures = facturaRepository.findByReservaId(id);
       model.addAttribute("factures", factures);
    
       
       
       if (!factures.isEmpty()) {
        Factura factura = factures.get(0);
        Reserva reserva = factura.getReserva();
        Client client = reserva.getClient();

        model.addAttribute("nom", client.getNom());
        model.addAttribute("cognom", client.getCognom());
    }


    Optional<Hotel> hotel = this.hotelRepository.findById(id);
       if (hotel.isPresent()) {
           session.setAttribute("id_hotel", id);
           Hotel h = hotel.get(); 
           model.addAttribute("hotel", h.getNom());
       }

       return "facturas/facturas"; //canviar a admin?
   }


   @PostMapping("/admin/reserva/confirmar/{id}")
   public String confirmarReserva(@PathVariable Long id, Model model){

    Optional <Reserva> reserva = reservaRepository.findById(id);


    Reserva reservaconf = reserva.get();
    reservaconf.getHabitacio().setEstat(EstatHabitacio.OCUPADA);

    reservaRepository.save(reservaconf);

    return "redirect:/";
   }


   @GetMapping("/admin/reserva/editarReserva/{id}")
   public String FormulariEditarReserva(@PathVariable Long id, Model model) {

    Reserva reserva = reservaRepository.findById(id).orElse(null);
    
    List<Habitacio> habitacions = habitacioRepository.findAll();
    
    model.addAttribute("reserva", reserva);
    model.addAttribute("habitacions", habitacions);
    model.addAttribute("tipus_reserva", Arrays.asList(TipusReserva.values()));
    return "admin/editarReserva";
}

@PostMapping("/admin/reserva/actualizarReserva/{id}")
public String actualitzarReserva(@PathVariable Long id, @ModelAttribute Reserva reservaActualizada) {
    Reserva reservacambiada = reservaRepository.findById(id).orElse(null);

    if (reservacambiada != null) {
        reservacambiada.setHabitacio(reservaActualizada.getHabitacio());
        if (reservaActualizada.getData_inici() != null) {
            reservacambiada.setData_inici(reservaActualizada.getData_inici());
        }
        if (reservaActualizada.getData_fi() != null) {
            reservacambiada.setData_fi(reservaActualizada.getData_fi());
        }
        reservacambiada.setTipus_reserva(reservaActualizada.getTipus_reserva());

        reservaRepository.save(reservacambiada);
    }

    return "redirect:/admin/reserves";
}

   @PostMapping("/admin/reserva/eliminar/{id}")
   public String eliminarReserva(@PathVariable Long id, HttpSession session) {
   
       Optional<Reserva> reservaOpt = reservaRepository.findById(id);
   
       Reserva reserva = reservaOpt.get();

       Habitacio habitacio = reserva.getHabitacio();
       habitacio.setEstat(EstatHabitacio.DISPONIBLE);
       habitacioRepository.save(habitacio);
   
   
       reservaRepository.delete(reserva);
       return "redirect:/"; 
   }
    
}
