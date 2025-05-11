package com.hotel.hotel.controller;

import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.hotel.hotel.model.*;
import com.hotel.hotel.repository.ClientsRepository;
import com.hotel.hotel.repository.FacturaRepository;
import com.hotel.hotel.repository.HabitacioRepository;
import com.hotel.hotel.repository.HotelRepository;
import com.hotel.hotel.repository.ReservaRepository;
import com.hotel.hotel.repository.UsuariRepository;

import jakarta.servlet.http.HttpSession;

@Controller
public class ReservesController {
    
 
private ReservaRepository reservaRepository;

    private ClientsRepository clientsRepository;
    private HabitacioRepository habitacioRepository;
    private UsuariRepository usuariRepository;
    private HotelRepository hotelRepository;
    private FacturaRepository facturaRepository;

   public ReservesController(ReservaRepository reservaRepository, ClientsRepository clientsRepository, HabitacioRepository habitacioRepository, UsuariRepository usuariRepository, HotelRepository hotelRepository, FacturaRepository facturaRepository) {   
       this.reservaRepository = reservaRepository;
       this.clientsRepository = clientsRepository;
       this.habitacioRepository = habitacioRepository;
       this.usuariRepository = usuariRepository;
       this.hotelRepository = hotelRepository;
       this.facturaRepository = facturaRepository;
   }




   @GetMapping("/reserves/reserves") // llistar reserves de cada client
   public String reservesClient(Model model){
    Authentication auth = SecurityContextHolder.getContext().getAuthentication();
    String emailUsuario = auth.getName();
    Optional<Usuari> usuariOpt = usuariRepository.findByEmail(emailUsuario);


    if (!usuariOpt.isPresent()) {
        return "redirect:/login?error";
    }

    Usuari usuari = usuariOpt.get();

    if (!(usuari.getPersona() instanceof Client)) {
        model.addAttribute("error", "Només els clients poden veure les seves reserves.");
        return "error";
    }

    Client client = (Client) usuari.getPersona();

    List<Reserva> reserves = reservaRepository.findByClientId(client.getId());
    model.addAttribute("reserves", reserves);

    return "reserves/reserves";
   }


    @GetMapping("/hotels/{id}/reserves") // getmapping per als formularis reserva
   public String mostrarFormularioReserva(@PathVariable Long id, Model model, HttpSession session) {
       Authentication auth = SecurityContextHolder.getContext().getAuthentication();
       String emailUsuario = auth.getName();
       Optional<Usuari> optionalUsuari = usuariRepository.findByEmail(emailUsuario);
   
       if (!optionalUsuari.isPresent()) {
           return "redirect:/login?error";
       }
   
       Usuari usuari = optionalUsuari.get();
       model.addAttribute("nom", usuari.getPersona().getNom());
       model.addAttribute("cognom", usuari.getPersona().getCognom());
   
       
       if (!(usuari.getPersona() instanceof Client)) {
           model.addAttribute("error", "Només els clients poden realitzar reserves.");
           return "error"; 
       }
   
       Client client = (Client) usuari.getPersona();
       
       Optional<Hotel> hotelopt = hotelRepository.findById(id);
       if (!hotelopt.isPresent()) {
           model.addAttribute("errorHotel", "Hotel no encontrado");
           return "error";
       }
   
       Hotel hotel = hotelopt.get();
       session.setAttribute("id_hotel", hotel.getId());
       model.addAttribute("hotel", hotel);
   
       List<Habitacio> habitacionsDisponibles = habitacioRepository.findByHotelIdAndEstat(id, EstatHabitacio.DISPONIBLE);
       model.addAttribute("habitacions", habitacionsDisponibles);
   
       Reserva novaReserva = new Reserva();
       novaReserva.setClient(client);
       session.setAttribute("client", client);
   
       model.addAttribute("reserva", novaReserva);
       model.addAttribute("client", client);
   
       return "FORMULARIS/Reserva";
   }
    

   @GetMapping("/facturas/facturas/{id}")
   public String llistarFactures(Model model, HttpSession session, @PathVariable Long id) {
    Authentication auth = SecurityContextHolder.getContext().getAuthentication();
       String emailUsuario = auth.getName();
       Optional<Usuari> optionalUsuari = usuariRepository.findByEmail(emailUsuario);


       List <Factura> factures = facturaRepository.findByReservaId(id);
       model.addAttribute("factures", factures);
    
       Optional<Hotel> hotel = this.hotelRepository.findById(id);
       if (optionalUsuari.isPresent()) {
        model.addAttribute("nom", optionalUsuari.get().getPersona().getNom());
        model.addAttribute("cognom", optionalUsuari.get().getPersona().getCognom());
    }

       if (hotel.isPresent()) {
           session.setAttribute("id_hotel", id);
           Hotel h = hotel.get(); 
           model.addAttribute("hotel", h.getNom());
       }

       return "facturas/facturas";
   }



@PostMapping("/reservahabitacio")
public String reservarhabitacio(Model model, @ModelAttribute Reserva reserva, HttpSession session, @RequestParam String metode_pagament) {
    Authentication auth = SecurityContextHolder.getContext().getAuthentication();
    String emailUsuario = auth.getName();
    Optional<Usuari> optionalUsuari = usuariRepository.findByEmail(emailUsuario);

    reserva.setData_reserva(LocalDate.now());

    Optional<Habitacio> habitacioOpt = habitacioRepository.findById(reserva.getHabitacio().getId());
        if (habitacioOpt.isEmpty()) {
            model.addAttribute("error", "Habitación no encontrada");
            return "error";
        }
    Habitacio habitacio = habitacioOpt.get();

    Client client = (Client) session.getAttribute("client");
    reserva.setClient(client);
    reserva.setHabitacio(habitacio);

   
    


    long diesEstada = ChronoUnit.DAYS.between(reserva.getData_inici(), reserva.getData_fi());


    if (diesEstada<=0) {
        model.addAttribute("error de reserva", "La data d'inici ha de ser anterior a la data de fi");
        return "error";
    }


    double preuhabitaMP = habitacio.getPreuNitMP();
    double preuhabitaAD = habitacio.getPreuNitAD();


    double preuIVA = 1.0;

    if (reserva.getTipus_reserva() == TipusReserva.MP) {
        preuIVA = 1.5;
        double preuMP = diesEstada * preuhabitaMP * preuIVA;
        double iva = preuMP * (reserva.getTipus_iva() / 100);
        double total  = preuMP + iva;
        reserva.setPreu_total_reserva(total);
    }


    if (reserva.getTipus_reserva() == TipusReserva.AD) {
        preuIVA = 1.2;
        double preuAD = diesEstada * preuhabitaAD * preuIVA;
        double iva = preuAD * (reserva.getTipus_iva() / 100);
        double total  = preuAD + iva;
        reserva.setPreu_total_reserva(total);
    }
   

    habitacio.setEstat(EstatHabitacio.RESERVADA);
    habitacioRepository.save(habitacio);

    
    double preciofactura = reserva.getPreu_total_reserva();
    
    double iva = reserva.getTipus_iva();
    double baseimposable = preciofactura / (1 + (iva / 100.0));


    Factura factura = new Factura();
    factura.setData_emisio(reserva.getData_reserva());
    factura.setMetode_pagament(metode_pagament);
    factura.setBase_imposable(baseimposable);
    factura.setIva(reserva.getTipus_iva());
    factura.setTotal(preciofactura);

    
    factura.setReserva(reserva);
    reserva.setFactura(factura);

    
    reservaRepository.save(reserva);



    model.addAttribute("reserva", reserva);

    return "redirect:/reserves/reserves";
}


@PostMapping("/hotels/{id}/reserva2")
public String comprobarDisponibilidad(@PathVariable Long id, @ModelAttribute("reserva") Reserva reservaForm, Model model, HttpSession session) {

    Authentication auth = SecurityContextHolder.getContext().getAuthentication();
    String emailUsuario = auth.getName();
    Optional<Usuari> optionalUsuari = usuariRepository.findByEmail(emailUsuario);

    if (!optionalUsuari.isPresent()) {
        return "redirect:/login?error";
    }

    Usuari usuari = optionalUsuari.get();
    Client client = (Client) usuari.getPersona();

    
    if (client == null) {
        model.addAttribute("error", "No se ha encontrado un cliente válido.");
        return "error";  
    }

    reservaForm.setClient(client); 

    
    session.setAttribute("data_inici", reservaForm.getData_inici());
    session.setAttribute("data_fi", reservaForm.getData_fi());

    
    List<Reserva> reservasSolapadas = reservaRepository.findReservasSolapadas(id, reservaForm.getData_inici(), reservaForm.getData_fi());

    
        List<Long> habitacionesOcupadasIds = new ArrayList<>();
        for (Reserva r : reservasSolapadas) {
            if (r.getHabitacio() != null) {
                habitacionesOcupadasIds.add(r.getHabitacio().getId());
            }
        }

     //   List<Habitacio> todasHabitaciones = habitacioRepository.findByHotelIdAndEstat(id, EstatHabitacio.DISPONIBLE);
          List<Habitacio> todasHabitaciones = habitacioRepository.findByHotelId(id);

        List<Habitacio> habitacionesDisponibles = new ArrayList<>();

        for (Habitacio h : todasHabitaciones) {
            if (!habitacionesOcupadasIds.contains(h.getId())) {
                habitacionesDisponibles.add(h);
            }
    }



    model.addAttribute("habitacionsDisponibles", habitacionesDisponibles);
    model.addAttribute("client", client);
    model.addAttribute("hotel", hotelRepository.findById(id).orElse(null));
    model.addAttribute("reserva", reservaForm);
    model.addAttribute("nom", usuari.getPersona().getNom());
    model.addAttribute("cognom", usuari.getPersona().getCognom());

    return "FORMULARIS/Reserva2";
}





  
@PostMapping("/reserva/eliminar/{id}")
public String eliminarReserva(@PathVariable Long id, HttpSession session) {
       Authentication auth = SecurityContextHolder.getContext().getAuthentication();
       String emailUsuario = auth.getName();
       Optional<Usuari> usuariOpt = usuariRepository.findByEmail(emailUsuario);


    Usuari usuari = usuariOpt.get();
    Client client = (Client) usuari.getPersona();

    Optional<Reserva> reservaOpt = reservaRepository.findByIdAndClient_Id(id, client.getId());

    if (reservaOpt.isPresent()) {

        Reserva reserva = reservaOpt.get();
        LocalDate ahora = LocalDate.now();
        LocalDate limite = reserva.getData_inici().minusDays(1);
        if (!ahora.isBefore(limite)) {
            return "redirect:/reserves/error";
    }
    



    Habitacio habitacio = reserva.getHabitacio();
    habitacio.setEstat(EstatHabitacio.DISPONIBLE);
    habitacioRepository.save(habitacio);


    reservaRepository.delete(reserva);
   
    }
    return "redirect:/"; 



}

@GetMapping("/reserves/error")
public String errorCancelarReserva(){
return "/reserves/error";
}
}