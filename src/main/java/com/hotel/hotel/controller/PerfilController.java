package com.hotel.hotel.controller;

import java.util.Optional;

import org.springframework.security.core.Authentication;

import org.springframework.security.core.context.SecurityContextHolder;

import com.hotel.hotel.model.*;

import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

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
    model.addAttribute("email", usuari.get().getEmail());
    model.addAttribute("nom", usuari.get().getPersona().getNom());
 }


 return "client/home"; 

 }

 
 @GetMapping("/client/editarperfil")
 public String editarperfil(Model model) {
   Authentication auth = SecurityContextHolder.getContext().getAuthentication();
   String emailUsuario = auth.getName();

   Optional<Usuari> usuariOpt = usuariRepository.findByEmail(emailUsuario);
   if (usuariOpt.isPresent()) {
      model.addAttribute("email", usuariOpt.get().getEmail());
      model.addAttribute("nom", usuariOpt.get().getPersona().getNom());
       Usuari usuari = usuariOpt.get();
       if (usuari.getPersona() instanceof Client client) {
           model.addAttribute("client", client);
           return "FORMULARIS/editarperfil";
       }
   }
   return "redirect:/login?error";
}


 
@PostMapping("/client/editarperfil")
public String perfilnuevo(String nom, String cognom, String email, String telefon, String password) {
    Authentication auth = SecurityContextHolder.getContext().getAuthentication();
    String emailUsuario = auth.getName();

    Optional<Usuari> usuariOpt = usuariRepository.findByEmail(emailUsuario);



    if (usuariOpt.isPresent()) {
        Usuari usuari = usuariOpt.get();
        if (usuari.getPersona() instanceof Client client) {
         String emailOriginal = usuari.getEmail();
         String passwordOriginal = usuari.getPassword();
            client.setNom(nom);
            client.setCognom(cognom);
            client.setTelefon(telefon);
            client.setEmail(email);
            usuari.setEmail(email);

            boolean cambioCorreo = !emailOriginal.equalsIgnoreCase(email);
            boolean cambioPassword = password != null && !password.isBlank();


            if (cambioPassword) {
                usuari.setPassword(password); 
            }

            usuariRepository.save(usuari);

            

            if (cambioCorreo || cambioPassword) {
                return "redirect:/login?error"; 
            }

            
            return "redirect:/client/home";
        }
    }


    return "redirect:/login?error";
}
 


}