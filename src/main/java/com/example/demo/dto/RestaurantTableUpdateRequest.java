package com.example.demo.dto;

import jakarta.validation.constraints.Positive;

public record RestaurantTableUpdateRequest(
        @Positive
        Integer tableNumber,

        @Positive
        Integer capacity
) {
}
