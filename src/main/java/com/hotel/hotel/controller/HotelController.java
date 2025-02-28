package com.hotel.hotel.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PathVariable;

//import com.hotel.hotel.model.Habitacio;
import com.hotel.hotel.model.Hotel;
import com.hotel.hotel.repository.HotelRepository;

@Controller
public class HotelController {


    private final HotelRepository hotelRepository;

    public HotelController(HotelRepository hotelRepository) {
        this.hotelRepository = hotelRepository;
    }

    @GetMapping("/hotels")
    public String showCategories(Model model) {
        List<Hotel> hotels = hotelRepository.findAll();
        model.addAttribute("hotels", hotels);
        return "hotels/llistat"; 
    }


    

}
