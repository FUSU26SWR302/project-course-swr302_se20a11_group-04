package com.rms.restaurant_management_system.dto.response;

import com.rms.restaurant_management_system.enums.OrderStatus;
import com.rms.restaurant_management_system.enums.OrderType;
import com.rms.restaurant_management_system.enums.PaymentStatus;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
public class OrderResponse {
    private Long id;
    private Long userId;
    private String userFullName;
    private OrderType orderType;
    private OrderStatus status;
    private PaymentStatus paymentStatus;
    private BigDecimal totalAmount;
    private BigDecimal discountAmount;
    private BigDecimal finalAmount;
    private String deliveryAddress;
    private String tableNumber;
    private String couponCode;
    private String note;
    private List<OrderItemResponse> items;
    private LocalDateTime createdAt;
}
