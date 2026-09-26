package com.example.demo.controller;

import com.example.demo.dto.RestaurantTableCreateRequest;
import com.example.demo.dto.RestaurantTableResponse;
import com.example.demo.dto.RestaurantTableUpdateRequest;
import com.example.demo.service.RestaurantTableService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class RestaurantTableController {
    private final RestaurantTableService restaurantTableService;

    @PostMapping("/api/restaurants/{restaurantId}/tables")
    public ResponseEntity<RestaurantTableResponse> create(
            @PathVariable Long restaurantId,
            @Valid @RequestBody RestaurantTableCreateRequest request
    ) {
        RestaurantTableResponse response = restaurantTableService.create(restaurantId, request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("/api/restaurants/{restaurantId}/tables")
    public ResponseEntity<List<RestaurantTableResponse>> getByRestaurant(@PathVariable Long restaurantId) {
        return ResponseEntity.ok(restaurantTableService.getByRestaurant(restaurantId));
    }

    @PatchMapping("/api/tables/{tableId}")
    public ResponseEntity<RestaurantTableResponse> update(
            @PathVariable Long tableId,
            @Valid @RequestBody RestaurantTableUpdateRequest request
    ) {
        return ResponseEntity.ok(restaurantTableService.update(tableId, request));
    }

    @DeleteMapping("/api/tables/{tableId}")
    public ResponseEntity<Void> delete(@PathVariable Long tableId) {
        restaurantTableService.delete(tableId);
        return ResponseEntity.noContent().build();
    }
}
