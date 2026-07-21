package com.rms.restaurant_management_system.controller;

import com.rms.restaurant_management_system.dto.request.TableRequest;
import com.rms.restaurant_management_system.dto.response.TableResponse;
import com.rms.restaurant_management_system.enums.TableStatus;
import com.rms.restaurant_management_system.service.interfaces.TableService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tables")
@RequiredArgsConstructor
public class TableController {

    private final TableService tableService;

    @GetMapping
    public ResponseEntity<List<TableResponse>> getAllTables() {
        return ResponseEntity.ok(tableService.getAllTables());
    }

    @GetMapping("/available")
    public ResponseEntity<List<TableResponse>> getAvailableTables() {
        return ResponseEntity.ok(tableService.getAvailableTables());
    }

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<TableResponse> createTable(@Valid @RequestBody TableRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(tableService.createTable(request));
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<TableResponse> updateTable(@PathVariable Long id, @Valid @RequestBody TableRequest request) {
        return ResponseEntity.ok(tableService.updateTable(id, request));
    }

    @PutMapping("/{id}/status")
    @PreAuthorize("hasRole('ADMIN') or hasRole('STAFF')")
    public ResponseEntity<TableResponse> updateStatus(@PathVariable Long id, @RequestParam TableStatus status) {
        return ResponseEntity.ok(tableService.updateStatus(id, status));
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> deleteTable(@PathVariable Long id) {
        tableService.deleteTable(id);
        return ResponseEntity.noContent().build();
    }
}
