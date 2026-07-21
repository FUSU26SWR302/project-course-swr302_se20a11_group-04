package com.rms.restaurant_management_system.repository;

import com.rms.restaurant_management_system.entity.Reservation;
import com.rms.restaurant_management_system.enums.ReservationStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {
    List<Reservation> findByUserIdOrderByCreatedAtDesc(Long userId);
    List<Reservation> findByStatusOrderByReservedAtAsc(ReservationStatus status);
}
