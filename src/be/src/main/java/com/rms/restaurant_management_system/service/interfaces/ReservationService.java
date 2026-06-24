package com.rms.restaurant_management_system.service.interfaces;

import com.rms.restaurant_management_system.dto.request.ReservationRequest;
import com.rms.restaurant_management_system.dto.response.ReservationResponse;
import com.rms.restaurant_management_system.enums.ReservationStatus;

import java.util.List;

public interface ReservationService {
    ReservationResponse create(ReservationRequest request, String email);
    List<ReservationResponse> getMyReservations(String email);
    List<ReservationResponse> getAllReservations();
    ReservationResponse updateStatus(Long id, ReservationStatus status);
    void cancel(Long id, String email);
}
