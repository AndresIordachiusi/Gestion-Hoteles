package com.hotel.hotel.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import com.hotel.hotel.model.Habitacio;


public interface HabitacioRepository extends JpaRepository<Habitacio, Long> {

    List<Habitacio> findByHotelId(int idhotel);
}

    

