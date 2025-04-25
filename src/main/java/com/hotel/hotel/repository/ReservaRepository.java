package com.hotel.hotel.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.hotel.hotel.model.*;

import com.hotel.hotel.model.Reserva;

@Repository
public interface ReservaRepository extends JpaRepository<Reserva, Long> {
    

Optional <Reserva> findById(Long id);
List <Reserva> findByClientId(Long clientId);
Optional <Reserva> findByIdAndClient_Id(Long id, Long clientId);


}
