package com.hotel.hotel.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hotel.hotel.model.*;

public interface ValoracioRepository extends JpaRepository<Valoracio, Long>{


    
    List<Valoracio> findByClientId(Long clientId);

    
}
