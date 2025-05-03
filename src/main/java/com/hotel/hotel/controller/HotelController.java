package com.hotel.hotel.controller;

import java.util.List;

import java.util.Optional;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.PathVariable;

import com.hotel.hotel.model.Habitacio;
import com.hotel.hotel.model.Hotel;
import com.hotel.hotel.model.Usuari;
import com.hotel.hotel.repository.HotelRepository;
import com.hotel.hotel.repository.UsuariRepository;

import jakarta.servlet.http.HttpSession;


@Controller
public class HotelController {


    private final HotelRepository hotelRepository;
    private UsuariRepository usuariRepository;

    public HotelController(HotelRepository hotelRepository, UsuariRepository usuariRepository) {
        this.hotelRepository = hotelRepository;
        this.usuariRepository = usuariRepository;
    }

    @GetMapping("/hotels")
    public String showCategories(Model model) {
        List<Hotel> hotels = hotelRepository.findAll();
        model.addAttribute("hotels", hotels);
        return "hotels/llistat"; 
    }

    @GetMapping("/")
    public String index(Model model) {
    Authentication auth = SecurityContextHolder.getContext().getAuthentication();
    String email = auth.getName();
    Optional<Usuari> optionalUsuari = usuariRepository.findByEmail(email);
    

    if (optionalUsuari.isPresent()) {
        String rol = optionalUsuari.get().getRol(); 
        model.addAttribute("rol", rol.replace("ROLE_", ""));
    }

        return "principal";
    }

    @GetMapping("/hotels/{id}")
    public String infohotel(@PathVariable Long id, Model model, HttpSession session) {

        Optional<Hotel> opt = this.hotelRepository.findById(id);
        if (opt.isPresent()) {
            session.setAttribute("id_hotel", id);
            Hotel h = opt.get();
            model.addAttribute("hotel", h);
            List<Habitacio> habitacions = h.getHabitacions();
            model.addAttribute("habitacions", habitacions);
            return "habitacions/llistat";

        }

        return "error";     
    }    

}
