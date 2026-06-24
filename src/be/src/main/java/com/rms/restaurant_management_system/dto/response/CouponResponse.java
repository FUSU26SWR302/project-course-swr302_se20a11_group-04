package com.rms.restaurant_management_system.dto.response;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Builder
public class CouponResponse {
    private Long id;
    private String code;
    private Integer discountPercent;
    private BigDecimal discountAmount;
    private BigDecimal minOrderAmount;
    private Integer maxUses;
    private Integer usedCount;
    private LocalDateTime expiresAt;
    private Boolean isActive;
}
