package com.rms.restaurant_management_system.dto.response;

import com.rms.restaurant_management_system.enums.TableStatus;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TableResponse {
    private Long id;
    private String tableNumber;
    private Integer capacity;
    private TableStatus status;
    private String location;
}
