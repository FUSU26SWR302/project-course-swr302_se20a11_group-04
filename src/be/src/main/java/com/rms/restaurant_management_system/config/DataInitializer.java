package com.rms.restaurant_management_system.config;

import com.rms.restaurant_management_system.entity.RestaurantTable;
import com.rms.restaurant_management_system.entity.Role;
import com.rms.restaurant_management_system.enums.RoleName;
import com.rms.restaurant_management_system.repository.RestaurantTableRepository;
import com.rms.restaurant_management_system.repository.RoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Component
@RequiredArgsConstructor
public class DataInitializer implements CommandLineRunner {

    private final RoleRepository roleRepository;
    private final RestaurantTableRepository tableRepository;

    @Override
    public void run(String... args) {
        // Seed roles
        Arrays.stream(RoleName.values()).forEach(roleName -> {
            if (roleRepository.findByName(roleName).isEmpty()) {
                roleRepository.save(Role.builder().name(roleName).build());
            }
        });

        // Seed tables
        if (tableRepository.count() == 0) {
            List<RestaurantTable> tables = List.of(
                RestaurantTable.builder().tableNumber("T01").capacity(2).location("Tầng 1").build(),
                RestaurantTable.builder().tableNumber("T02").capacity(2).location("Tầng 1").build(),
                RestaurantTable.builder().tableNumber("T03").capacity(4).location("Tầng 1").build(),
                RestaurantTable.builder().tableNumber("T04").capacity(4).location("Tầng 1").build(),
                RestaurantTable.builder().tableNumber("T05").capacity(6).location("Tầng 1").build(),
                RestaurantTable.builder().tableNumber("T06").capacity(6).location("Tầng 2").build(),
                RestaurantTable.builder().tableNumber("T07").capacity(8).location("Tầng 2").build(),
                RestaurantTable.builder().tableNumber("T08").capacity(8).location("Tầng 2").build(),
                RestaurantTable.builder().tableNumber("T09").capacity(4).location("Ngoài trời").build(),
                RestaurantTable.builder().tableNumber("T10").capacity(4).location("Ngoài trời").build()
            );
            tableRepository.saveAll(tables);
        }
    }
}
