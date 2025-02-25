package com.hotel.hotel.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hotel.hotel.model.Hotel;

@Repository
public interface HotelRepository extends JpaRepository<Hotel, Long>{
    

    //Optional<Hotel> findByNom(String nom);
}
