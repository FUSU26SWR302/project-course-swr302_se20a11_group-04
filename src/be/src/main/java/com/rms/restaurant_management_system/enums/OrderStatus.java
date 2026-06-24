package com.rms.restaurant_management_system.enums;

public enum OrderStatus {
    PENDING,        // Chờ xác nhận
    CONFIRMED,      // Đã xác nhận
    PREPARING,      // Đang chuẩn bị
    READY,          // Sẵn sàng
    DELIVERING,     // Đang giao (online)
    COMPLETED,      // Hoàn thành
    CANCELLED       // Đã hủy
}
