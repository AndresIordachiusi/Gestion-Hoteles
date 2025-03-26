package com.hotel.hotel.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hotel.hotel.model.Usuari;


@Repository
public interface UsuariRepository extends JpaRepository<Usuari, Long> {
    
}
