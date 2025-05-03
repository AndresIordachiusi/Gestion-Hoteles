package com.hotel.hotel.controller;

import java.util.Optional;

import org.springframework.boot.autoconfigure.neo4j.Neo4jProperties.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.hotel.hotel.model.Usuari;
import com.hotel.hotel.repository.UsuariRepository;





@Controller
public class LoginController {

    private UsuariRepository usuariRepository;

@GetMapping("/login")
public String login() {
    
    return "login";
}

/* 
@GetMapping("admin/dashboard")
public String dashboard() {
    
    return "admin/dashboard"; 

}
*/



/* 
@GetMapping("/client/home")
public String homedelcliente(){


    return "/client/home";
}
*/
}
