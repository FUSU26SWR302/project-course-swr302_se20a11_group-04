package com.rms.restaurant_management_system.dto.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class TableRequest {

    @NotBlank(message = "Table number is required")
    private String tableNumber;

    @NotNull
    @Min(value = 1)
    private Integer capacity;

    private String location;
}
