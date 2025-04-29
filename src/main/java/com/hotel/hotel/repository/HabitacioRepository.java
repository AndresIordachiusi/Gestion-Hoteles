package com.hotel.hotel.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.hotel.hotel.model.*;

@Repository
public interface HabitacioRepository extends JpaRepository<Habitacio, Long> {

    Optional<Habitacio> findById(Long id);
    Optional<Habitacio> findByHotel(Hotel hotel);
    List<Habitacio> findByHotelId(Long idhotel);
    List<Habitacio> findByHotelIdAndEstat(Long idhotel, EstatHabitacio estat);

    @Query("SELECT h.hotel.id, COUNT(h) FROM Habitacio h GROUP BY h.hotel.id")

    List<Habitacio> findByHotelId(int idhotel);
}

    

