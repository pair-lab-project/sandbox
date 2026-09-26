package com.example.demo.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record RestaurantTableCreateRequest(
        @NotNull
        @Positive
        Integer tableNumber,

        @NotNull
        @Positive
        Integer capacity
) {
}