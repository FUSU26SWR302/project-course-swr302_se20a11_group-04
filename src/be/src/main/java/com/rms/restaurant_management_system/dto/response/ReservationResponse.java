package com.rms.restaurant_management_system.dto.response;

import com.rms.restaurant_management_system.enums.ReservationStatus;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class ReservationResponse {
    private Long id;
    private Long userId;
    private String userFullName;
    private String guestName;
    private String guestPhone;
    private Integer guestCount;
    private LocalDateTime reservedAt;
    private ReservationStatus status;
    private String tableNumber;
    private String note;
    private LocalDateTime createdAt;
}
