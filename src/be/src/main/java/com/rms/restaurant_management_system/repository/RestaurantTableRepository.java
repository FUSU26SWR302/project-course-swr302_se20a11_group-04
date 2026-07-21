package com.rms.restaurant_management_system.repository;

import com.rms.restaurant_management_system.entity.RestaurantTable;
import com.rms.restaurant_management_system.enums.TableStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RestaurantTableRepository extends JpaRepository<RestaurantTable, Long> {
    List<RestaurantTable> findByStatus(TableStatus status);
    boolean existsByTableNumber(String tableNumber);
}
