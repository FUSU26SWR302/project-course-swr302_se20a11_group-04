package com.rms.restaurant_management_system.dto.request;

import com.rms.restaurant_management_system.enums.OrderType;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.List;

@Data
public class OrderRequest {

    @NotNull(message = "Order type is required")
    private OrderType orderType;

    @NotEmpty(message = "Items cannot be empty")
    private List<OrderItemRequest> items;

    private Long tableId;         // cho DINE_IN
    private String deliveryAddress; // cho ONLINE
    private String couponCode;
    private String note;
}
