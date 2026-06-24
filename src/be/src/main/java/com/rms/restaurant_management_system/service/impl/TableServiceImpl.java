package com.rms.restaurant_management_system.service.impl;

import com.rms.restaurant_management_system.dto.request.TableRequest;
import com.rms.restaurant_management_system.dto.response.TableResponse;
import com.rms.restaurant_management_system.entity.RestaurantTable;
import com.rms.restaurant_management_system.enums.TableStatus;
import com.rms.restaurant_management_system.exception.BadRequestException;
import com.rms.restaurant_management_system.exception.ResourceNotFoundException;
import com.rms.restaurant_management_system.repository.RestaurantTableRepository;
import com.rms.restaurant_management_system.service.interfaces.TableService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TableServiceImpl implements TableService {

    private final RestaurantTableRepository tableRepository;

    @Override
    public List<TableResponse> getAllTables() {
        return tableRepository.findAll().stream().map(this::toResponse).collect(Collectors.toList());
    }

    @Override
    public List<TableResponse> getAvailableTables() {
        return tableRepository.findByStatus(TableStatus.AVAILABLE).stream().map(this::toResponse).collect(Collectors.toList());
    }

    @Override
    public TableResponse createTable(TableRequest request) {
        if (tableRepository.existsByTableNumber(request.getTableNumber()))
            throw new BadRequestException("Table number already exists");
        RestaurantTable table = RestaurantTable.builder()
                .tableNumber(request.getTableNumber())
                .capacity(request.getCapacity())
                .location(request.getLocation())
                .build();
        return toResponse(tableRepository.save(table));
    }

    @Override
    public TableResponse updateTable(Long id, TableRequest request) {
        RestaurantTable table = findById(id);
        table.setTableNumber(request.getTableNumber());
        table.setCapacity(request.getCapacity());
        table.setLocation(request.getLocation());
        return toResponse(tableRepository.save(table));
    }

    @Override
    public TableResponse updateStatus(Long id, TableStatus status) {
        RestaurantTable table = findById(id);
        table.setStatus(status);
        return toResponse(tableRepository.save(table));
    }

    @Override
    public void deleteTable(Long id) {
        tableRepository.delete(findById(id));
    }

    private RestaurantTable findById(Long id) {
        return tableRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Table not found with id: " + id));
    }

    private TableResponse toResponse(RestaurantTable t) {
        return TableResponse.builder()
                .id(t.getId()).tableNumber(t.getTableNumber())
                .capacity(t.getCapacity()).status(t.getStatus()).location(t.getLocation())
                .build();
    }
}
