package com.hotel.hotel.repository;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.hotel.hotel.model.*;

import com.hotel.hotel.model.Reserva;

@Repository
public interface ReservaRepository extends JpaRepository<Reserva, Long> {
    

Optional <Reserva> findById(Long id);
List <Reserva> findByClientId(Long clientId);
Optional <Reserva> findByIdAndClient_Id(Long id, Long clientId);


@Query("SELECT r FROM Reserva r WHERE r.habitacio.hotel.id = :hotelId AND " + "r.data_inici < :dataFi AND r.data_fi > :dataInici")
List<Reserva> findReservasSolapadas(@Param("hotelId") Long hotelId, @Param("dataInici") LocalDate dataInici, @Param("dataFi") LocalDate dataFi);







}
