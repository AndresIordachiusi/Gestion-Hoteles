package com.hotel.hotel.controller;

import java.sql.Date;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
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
   
       Client client = (Client) usuari.getPersona();
       Reserva novaReserva = new Reserva();
       novaReserva.setClient(client);
       session.setAttribute("client", client);
   
       model.addAttribute("reserva", novaReserva);
       model.addAttribute("client", client);
   
       return "FORMULARIS/Reserva";
   }
   

@PostMapping("/reservahabitacio")
public String reservarhabitacio(Model model, @ModelAttribute Reserva reserva, BindingResult result, HttpSession session) {

    reserva.setData_reserva(Date.valueOf(LocalDate.now()));

    Habitacio habitacioForm = reserva.getHabitacio();

    Optional<Habitacio> habitacioOpt = habitacioRepository.findById(habitacioForm.getId());

    Habitacio habitacio = habitacioOpt.get();


    habitacio.setEstat(EstatHabitacio.OCUPADA);
    Client client = (Client) session.getAttribute("client");
    reserva.setClient(client);
    reserva.setHabitacio(habitacio);


    long diesEstada = ChronoUnit.DAYS.between(reserva.getData_inici().toLocalDate(), reserva.getData_fi().toLocalDate());


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

    reservaRepository.save(reserva);
    habitacioRepository.save(habitacio);
    return "redirect:/";
}




  
}

