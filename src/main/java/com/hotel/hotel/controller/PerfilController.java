package com.hotel.hotel.controller;

import java.util.ArrayList;

import java.util.List;

import java.util.Optional;



import org.springframework.security.core.Authentication;

import org.springframework.security.core.GrantedAuthority;

import org.springframework.security.core.context.SecurityContextHolder;

import org.springframework.security.core.userdetails.User;

import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.GetMapping;



import com.hotel.hotel.model.Usuari;

import com.hotel.hotel.repository.UsuariRepository;




@Controller

public class PerfilController {



 private UsuariRepository usuariRepository = null;



 public PerfilController(UsuariRepository usuariRepository){

 this.usuariRepository = usuariRepository;

 }



 @GetMapping("/perfil")

 public String perfil() {



 Authentication authentication = SecurityContextHolder.getContext().getAuthentication();



 // Obtenim el nom d'usuari (email en el nostre cas!)

 String username = authentication.getName();

 System.out.println("Usuari: " + username);

 

 Optional<Usuari> usuari = usuariRepository.findByEmail(username);



 return "principal"; // El nom de la vista que vulgueu carregar

 }

}