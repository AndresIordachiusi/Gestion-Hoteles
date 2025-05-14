package com.hotel.hotel.controller;

import org.springframework.stereotype.Controller;
import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
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
import com.hotel.hotel.repository.*;

import jakarta.servlet.http.HttpSession;


@Controller
public class ValoracioController {



    private UsuariRepository usuariRepository;
    private ValoracioRepository valoracioRepository;
    private HabitacioRepository habitacioRepository;
    private ReservaRepository reservaRepository;

public ValoracioController(UsuariRepository usuariRepository, ValoracioRepository valoracioRepository, HabitacioRepository habitacioRepository, ReservaRepository reservaRepository) {
        this.usuariRepository = usuariRepository;
        this.valoracioRepository = valoracioRepository;
        this.habitacioRepository = habitacioRepository;
        this.reservaRepository = reservaRepository;
    }






    @GetMapping("/valoracions/crear")
    public String novaResenya(Model model, @ModelAttribute Reserva reserva) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String email = auth.getName();
        Usuari usuari = usuariRepository.findByEmail(email).get();
        Client client = (Client) usuari.getPersona();
      

        model.addAttribute("valoracio", new Valoracio());
        return "reserves/valoracions";
    }



@GetMapping("/valoracio/client")
public String mostrarValoracions(Model model){
    Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String email = auth.getName();
        Usuari usuari = usuariRepository.findByEmail(email).get();
        Client client = (Client) usuari.getPersona();


        List<Valoracio> valoracions = valoracioRepository.findByClientId(client.getId());
        model.addAttribute("valoracions", valoracions);
        model.addAttribute("email", client.getEmail());

    return "";
}

@PostMapping("/valoracio/publicar")
    public String guardarResenya(@ModelAttribute Valoracio valoracio, @ModelAttribute Reserva reserva) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            String emailUsuario = auth.getName();
            Optional<Usuari> usuariOpt = usuariRepository.findByEmail(emailUsuario);

            

           

        
        Usuari usuari = usuariOpt.get();
        Client client = (Client) usuari.getPersona();

        valoracio.setClient(client);
        valoracio.setData_creacio(LocalDate.now());
        valoracio.setHora_creacio(LocalTime.now());        
        
        valoracioRepository.save(valoracio);
            
    
        
        return "redirect:/";
    }




    
}
