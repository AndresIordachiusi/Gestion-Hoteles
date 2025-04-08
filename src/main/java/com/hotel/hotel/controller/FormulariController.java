package com.hotel.hotel.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.ui.Model;
import com.hotel.hotel.model.*;
import com.hotel.hotel.repository.ClientsRepository;
import com.hotel.hotel.repository.EmpleatsRepository;
import com.hotel.hotel.repository.TascaRepository;
import com.hotel.hotel.repository.UsuariRepository;



@Controller
public class FormulariController {

    private EmpleatsRepository empleatsRepository;
    private TascaRepository tascaRepository;
    private ClientsRepository ClientsRepository;
    private UsuariRepository usuarisRepository;

    public FormulariController(EmpleatsRepository empleatsRepository, TascaRepository tascaRepository, ClientsRepository ClientsRepository, UsuariRepository usuarisRepository) {
        this.empleatsRepository = empleatsRepository;
        this.tascaRepository = tascaRepository;
        this.ClientsRepository = ClientsRepository;
        this.usuarisRepository = usuarisRepository;
    }
/* 
    @GetMapping("/formulari")
    public String mostrarFormulari(Model model) {
        model.addAttribute("usuari", new UsuariDTO());
        return "FORMULARIS/formulari";
    }
    
    @PostMapping("/processar")
    public String processar(@ModelAttribute UsuariDTO usuari, Model model) {
        model.addAttribute("usuari", usuari);
        return "FORMULARIS/resultat";
    }
        */
    @GetMapping("/empleats/llistat")
    public String llistatEmpleats(Model model) {
        model.addAttribute("empleats", empleatsRepository.findAll());
        return "empleats/llistat";
    }
 

    @GetMapping("/altaempleat")
    public String altaempleat(Model model) {
        List<Tasca> tasques = tascaRepository.findAll();
        model.addAttribute("tasques", tasques);
        model.addAttribute("empleat", new Empleat());
        return "FORMULARIS/altaempleat";
    }

    @PostMapping("/registrarEmpleat")
    public String registreEmpleat(Empleat empleat) {
        empleatsRepository.save(empleat);
        
        

        return "redirect:/empleats/llistat";
    }

    @GetMapping("/altaClient")
    public String registreClient(Model model) {
        model.addAttribute("client", new Client());
        return "FORMULARIS/altaClient";
    }   

    @PostMapping("/registrarClient")
    public String registreClient(Client client, @RequestParam("password") String password) {
        Usuari usuari = new Usuari();

        usuari.setEmail(client.getEmail());
        usuari.setActiu(true);
        usuari.setRol("ROLE_CLIENT");
        usuari.setPassword(password);
        usuari.setUsername(client.getNom());


        usuari = usuarisRepository.save(usuari);

        client.setUsuari(usuari);


        ClientsRepository.save(client);
        return "redirect:/login";
    }
 

    @GetMapping ("/clients/llistat")
   public String llistatClients(Model model) {
       model.addAttribute("clients", ClientsRepository.findAll());
       return "clients/llistat";
   }


}
