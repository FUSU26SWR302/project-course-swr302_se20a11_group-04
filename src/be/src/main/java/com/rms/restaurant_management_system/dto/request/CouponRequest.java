package com.rms.restaurant_management_system.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class CouponRequest {

    @NotBlank(message = "Coupon code is required")
    private String code;

    private Integer discountPercent;
    private BigDecimal discountAmount;
    private BigDecimal minOrderAmount;
    private Integer maxUses;
    private LocalDateTime expiresAt;
}
