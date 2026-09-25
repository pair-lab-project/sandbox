package com.example.demo.dto;

import java.time.LocalDateTime;
import java.time.LocalTime;

import com.example.demo.entity.Restaurant;
import com.example.demo.entity.RestaurantStatus;

public record RestaurantResponse(
        Long id,
        String name,
        String address,
        String phone,
        String description,
        LocalTime openingTime,
        LocalTime closingTime,
        RestaurantStatus status,
        Long ownerId,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {
    public static RestaurantResponse from(Restaurant restaurant) {
        return new RestaurantResponse(
                restaurant.getId(),
                restaurant.getName(),
                restaurant.getAddress(),
                restaurant.getPhone(),
                restaurant.getDescription(),
                restaurant.getOpeningTime(),
                restaurant.getClosingTime(),
                restaurant.getStatus(),
                restaurant.getOwnerId(),
                restaurant.getCreatedAt(),
                restaurant.getUpdatedAt()
        );
    }
}
