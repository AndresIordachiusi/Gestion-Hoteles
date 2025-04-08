package com.hotel.hotel.controller;

import java.util.Optional;

import org.springframework.security.core.Authentication;

import org.springframework.security.core.context.SecurityContextHolder;



import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.hotel.hotel.model.Usuari;

import com.hotel.hotel.repository.UsuariRepository;

import org.springframework.ui.Model;




@Controller

public class PerfilController {



 private UsuariRepository usuariRepository = null;



 public PerfilController(UsuariRepository usuariRepository){

 this.usuariRepository = usuariRepository;

 }



 @GetMapping("/client/home")

 public String perfil(Model model) {



 Authentication authentication = SecurityContextHolder.getContext().getAuthentication();



 // Obtenim el nom d'usuari (email en el nostre cas!)

 String username = authentication.getName();

 System.out.println("Usuari: " + username);



 Optional<Usuari> usuari = usuariRepository.findByEmail(username);

 if (usuari.isPresent()) {
    model.addAttribute("nom", usuari.get().getEmail());
 }


 return "client/home"; 

 }

 


}