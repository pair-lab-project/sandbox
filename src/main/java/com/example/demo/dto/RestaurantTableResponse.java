package com.example.demo.dto;

import com.example.demo.entity.RestaurantTable;
import com.example.demo.entity.TableStatus;

import java.time.LocalDateTime;

public record RestaurantTableResponse(
        Long id,
        Long restaurantId,
        Integer tableNumber,
        Integer capacity,
        TableStatus status,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {
    public static RestaurantTableResponse from(RestaurantTable table) {
        return new RestaurantTableResponse(
                table.getId(),
                table.getRestaurant().getId(),
                table.getTableNumber(),
                table.getCapacity(),
                table.getStatus(),
                table.getCreatedAt(),
                table.getUpdatedAt()
        );
    }
}