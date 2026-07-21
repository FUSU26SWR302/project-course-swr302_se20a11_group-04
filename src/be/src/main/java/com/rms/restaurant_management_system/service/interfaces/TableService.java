package com.rms.restaurant_management_system.service.interfaces;

import com.rms.restaurant_management_system.dto.request.TableRequest;
import com.rms.restaurant_management_system.dto.response.TableResponse;
import com.rms.restaurant_management_system.enums.TableStatus;

import java.util.List;

public interface TableService {
    List<TableResponse> getAllTables();
    List<TableResponse> getAvailableTables();
    TableResponse createTable(TableRequest request);
    TableResponse updateTable(Long id, TableRequest request);
    TableResponse updateStatus(Long id, TableStatus status);
    void deleteTable(Long id);
}
