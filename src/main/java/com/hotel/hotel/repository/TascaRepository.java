package com.hotel.hotel.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hotel.hotel.model.*;

@Repository
public interface TascaRepository extends JpaRepository<Tasca, Long>{
    

    Optional <Tasca> findById(Long id);

    //Optional<Tasca> findByDescripcio(String descripcio);
   // Optional<Tasca> findByEstat(EstatTasca estat);    
}
