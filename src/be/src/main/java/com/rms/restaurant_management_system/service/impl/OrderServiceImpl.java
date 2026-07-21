package com.rms.restaurant_management_system.service.impl;

import com.rms.restaurant_management_system.dto.request.OrderRequest;
import com.rms.restaurant_management_system.dto.response.OrderItemResponse;
import com.rms.restaurant_management_system.dto.response.OrderResponse;
import com.rms.restaurant_management_system.entity.*;
import com.rms.restaurant_management_system.enums.OrderStatus;
import com.rms.restaurant_management_system.enums.OrderType;
import com.rms.restaurant_management_system.enums.PaymentStatus;
import com.rms.restaurant_management_system.exception.BadRequestException;
import com.rms.restaurant_management_system.exception.ResourceNotFoundException;
import com.rms.restaurant_management_system.repository.*;
import com.rms.restaurant_management_system.service.interfaces.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final UserRepository userRepository;
    private final FoodRepository foodRepository;
    private final CouponRepository couponRepository;
    private final RestaurantTableRepository tableRepository;

    @Override
    @Transactional
    public OrderResponse createOrder(OrderRequest request, String email) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));

        if (request.getOrderType() == OrderType.ONLINE && (request.getDeliveryAddress() == null || request.getDeliveryAddress().isBlank()))
            throw new BadRequestException("Delivery address is required for online orders");

        // Build items
        List<OrderItem> items = new ArrayList<>();
        BigDecimal total = BigDecimal.ZERO;

        for (var itemReq : request.getItems()) {
            Food food = foodRepository.findById(itemReq.getFoodId())
                    .orElseThrow(() -> new ResourceNotFoundException("Food not found: " + itemReq.getFoodId()));
            BigDecimal subtotal = food.getPrice().multiply(BigDecimal.valueOf(itemReq.getQuantity()));
            items.add(OrderItem.builder()
                    .food(food).quantity(itemReq.getQuantity())
                    .unitPrice(food.getPrice()).subtotal(subtotal)
                    .note(itemReq.getNote()).build());
            total = total.add(subtotal);
        }

        // Apply coupon
        BigDecimal discount = BigDecimal.ZERO;
        Coupon coupon = null;
        if (request.getCouponCode() != null && !request.getCouponCode().isBlank()) {
            coupon = couponRepository.findByCode(request.getCouponCode().toUpperCase())
                    .orElseThrow(() -> new ResourceNotFoundException("Coupon not found"));
            if (!coupon.getIsActive()) throw new BadRequestException("Coupon is inactive");
            if (coupon.getExpiresAt() != null && coupon.getExpiresAt().isBefore(LocalDateTime.now()))
                throw new BadRequestException("Coupon expired");
            if (coupon.getMaxUses() != null && coupon.getUsedCount() >= coupon.getMaxUses())
                throw new BadRequestException("Coupon usage limit reached");

            if (coupon.getDiscountPercent() != null)
                discount = total.multiply(BigDecimal.valueOf(coupon.getDiscountPercent())).divide(BigDecimal.valueOf(100));
            else if (coupon.getDiscountAmount() != null)
                discount = coupon.getDiscountAmount();

            coupon.setUsedCount(coupon.getUsedCount() + 1);
            couponRepository.save(coupon);
        }

        BigDecimal finalAmount = total.subtract(discount).max(BigDecimal.ZERO);

        // Table
        RestaurantTable table = null;
        if (request.getTableId() != null) {
            table = tableRepository.findById(request.getTableId())
                    .orElseThrow(() -> new ResourceNotFoundException("Table not found"));
        }

        Order order = Order.builder()
                .user(user).table(table).coupon(coupon)
                .orderType(request.getOrderType())
                .totalAmount(total).discountAmount(discount).finalAmount(finalAmount)
                .deliveryAddress(request.getDeliveryAddress())
                .note(request.getNote())
                .build();

        items.forEach(item -> item.setOrder(order));
        order.setItems(items);

        return toResponse(orderRepository.save(order));
    }

    @Override
    public OrderResponse getOrderById(Long id) {
        return toResponse(findById(id));
    }

    @Override
    public List<OrderResponse> getMyOrders(String email) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));
        return orderRepository.findByUserIdOrderByCreatedAtDesc(user.getId())
                .stream().map(this::toResponse).collect(Collectors.toList());
    }

    @Override
    public List<OrderResponse> getAllOrders() {
        return orderRepository.findAllByOrderByCreatedAtDesc().stream().map(this::toResponse).collect(Collectors.toList());
    }

    @Override
    public OrderResponse updateStatus(Long id, OrderStatus status) {
        Order order = findById(id);
        order.setStatus(status);
        return toResponse(orderRepository.save(order));
    }

    @Override
    public OrderResponse updatePaymentStatus(Long id) {
        Order order = findById(id);
        order.setPaymentStatus(PaymentStatus.PAID);
        return toResponse(orderRepository.save(order));
    }

    @Override
    public void cancelOrder(Long id, String email) {
        Order order = findById(id);
        if (order.getStatus() == OrderStatus.COMPLETED || order.getStatus() == OrderStatus.DELIVERING)
            throw new BadRequestException("Cannot cancel this order");
        order.setStatus(OrderStatus.CANCELLED);
        orderRepository.save(order);
    }

    private Order findById(Long id) {
        return orderRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Order not found with id: " + id));
    }

    private OrderResponse toResponse(Order o) {
        List<OrderItemResponse> itemResponses = o.getItems().stream().map(i -> OrderItemResponse.builder()
                .id(i.getId()).foodId(i.getFood().getId()).foodName(i.getFood().getName())
                .foodImageUrl(i.getFood().getImageUrl()).quantity(i.getQuantity())
                .unitPrice(i.getUnitPrice()).subtotal(i.getSubtotal()).note(i.getNote())
                .build()).collect(Collectors.toList());

        return OrderResponse.builder()
                .id(o.getId())
                .userId(o.getUser() != null ? o.getUser().getId() : null)
                .userFullName(o.getUser() != null ? o.getUser().getFullName() : null)
                .orderType(o.getOrderType()).status(o.getStatus()).paymentStatus(o.getPaymentStatus())
                .totalAmount(o.getTotalAmount()).discountAmount(o.getDiscountAmount()).finalAmount(o.getFinalAmount())
                .deliveryAddress(o.getDeliveryAddress())
                .tableNumber(o.getTable() != null ? o.getTable().getTableNumber() : null)
                .couponCode(o.getCoupon() != null ? o.getCoupon().getCode() : null)
                .note(o.getNote()).items(itemResponses).createdAt(o.getCreatedAt())
                .build();
    }
}
