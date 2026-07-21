package com.rms.restaurant_management_system.controller;

import com.rms.restaurant_management_system.dto.request.ReservationRequest;
import com.rms.restaurant_management_system.dto.response.ReservationResponse;
import com.rms.restaurant_management_system.enums.ReservationStatus;
import com.rms.restaurant_management_system.service.interfaces.ReservationService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/reservations")
@RequiredArgsConstructor
public class ReservationController {

    private final ReservationService reservationService;

    @PostMapping
    public ResponseEntity<ReservationResponse> create(
            @Valid @RequestBody ReservationRequest request,
            @AuthenticationPrincipal UserDetails userDetails) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(reservationService.create(request, userDetails.getUsername()));
    }

    @GetMapping("/my")
    public ResponseEntity<List<ReservationResponse>> getMyReservations(
            @AuthenticationPrincipal UserDetails userDetails) {
        return ResponseEntity.ok(reservationService.getMyReservations(userDetails.getUsername()));
    }

    @GetMapping
    @PreAuthorize("hasRole('ADMIN') or hasRole('STAFF')")
    public ResponseEntity<List<ReservationResponse>> getAllReservations() {
        return ResponseEntity.ok(reservationService.getAllReservations());
    }

    @PutMapping("/{id}/status")
    @PreAuthorize("hasRole('ADMIN') or hasRole('STAFF')")
    public ResponseEntity<ReservationResponse> updateStatus(
            @PathVariable Long id, @RequestParam ReservationStatus status) {
        return ResponseEntity.ok(reservationService.updateStatus(id, status));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> cancel(
            @PathVariable Long id,
            @AuthenticationPrincipal UserDetails userDetails) {
        reservationService.cancel(id, userDetails.getUsername());
        return ResponseEntity.noContent().build();
    }
}
