package com.rms.restaurant_management_system.service.impl;

import com.rms.restaurant_management_system.dto.request.ReservationRequest;
import com.rms.restaurant_management_system.dto.response.ReservationResponse;
import com.rms.restaurant_management_system.entity.Reservation;
import com.rms.restaurant_management_system.entity.RestaurantTable;
import com.rms.restaurant_management_system.entity.User;
import com.rms.restaurant_management_system.enums.ReservationStatus;
import com.rms.restaurant_management_system.exception.BadRequestException;
import com.rms.restaurant_management_system.exception.ResourceNotFoundException;
import com.rms.restaurant_management_system.repository.ReservationRepository;
import com.rms.restaurant_management_system.repository.RestaurantTableRepository;
import com.rms.restaurant_management_system.repository.UserRepository;
import com.rms.restaurant_management_system.service.interfaces.ReservationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ReservationServiceImpl implements ReservationService {

    private final ReservationRepository reservationRepository;
    private final UserRepository userRepository;
    private final RestaurantTableRepository tableRepository;

    @Override
    public ReservationResponse create(ReservationRequest request, String email) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));
        RestaurantTable table = null;
        if (request.getTableId() != null) {
            table = tableRepository.findById(request.getTableId())
                    .orElseThrow(() -> new ResourceNotFoundException("Table not found"));
        }
        Reservation reservation = Reservation.builder()
                .user(user).table(table)
                .guestName(request.getGuestName())
                .guestPhone(request.getGuestPhone())
                .guestCount(request.getGuestCount())
                .reservedAt(request.getReservedAt())
                .note(request.getNote())
                .build();
        return toResponse(reservationRepository.save(reservation));
    }

    @Override
    public List<ReservationResponse> getMyReservations(String email) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));
        return reservationRepository.findByUserIdOrderByCreatedAtDesc(user.getId())
                .stream().map(this::toResponse).collect(Collectors.toList());
    }

    @Override
    public List<ReservationResponse> getAllReservations() {
        return reservationRepository.findAll().stream().map(this::toResponse).collect(Collectors.toList());
    }

    @Override
    public ReservationResponse updateStatus(Long id, ReservationStatus status) {
        Reservation r = reservationRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Reservation not found"));
        r.setStatus(status);
        return toResponse(reservationRepository.save(r));
    }

    @Override
    public void cancel(Long id, String email) {
        Reservation r = reservationRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Reservation not found"));
        if (r.getStatus() == ReservationStatus.COMPLETED)
            throw new BadRequestException("Cannot cancel a completed reservation");
        r.setStatus(ReservationStatus.CANCELLED);
        reservationRepository.save(r);
    }

    private ReservationResponse toResponse(Reservation r) {
        return ReservationResponse.builder()
                .id(r.getId())
                .userId(r.getUser().getId())
                .userFullName(r.getUser().getFullName())
                .guestName(r.getGuestName())
                .guestPhone(r.getGuestPhone())
                .guestCount(r.getGuestCount())
                .reservedAt(r.getReservedAt())
                .status(r.getStatus())
                .tableNumber(r.getTable() != null ? r.getTable().getTableNumber() : null)
                .note(r.getNote())
                .createdAt(r.getCreatedAt())
                .build();
    }
}
