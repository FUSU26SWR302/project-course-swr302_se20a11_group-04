package com.rms.restaurant_management_system.service.interfaces;

import com.rms.restaurant_management_system.dto.request.OrderRequest;
import com.rms.restaurant_management_system.dto.response.OrderResponse;
import com.rms.restaurant_management_system.enums.OrderStatus;

import java.util.List;

public interface OrderService {
    OrderResponse createOrder(OrderRequest request, String email);
    OrderResponse getOrderById(Long id);
    List<OrderResponse> getMyOrders(String email);
    List<OrderResponse> getAllOrders();
    OrderResponse updateStatus(Long id, OrderStatus status);
    OrderResponse updatePaymentStatus(Long id);
    void cancelOrder(Long id, String email);
}
