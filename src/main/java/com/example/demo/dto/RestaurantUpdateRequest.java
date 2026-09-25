package com.example.demo.dto;

import jakarta.validation.constraints.Size;

import java.time.LocalTime;

public record RestaurantUpdateRequest(
        @Size(max = 100)
        String name,

        @Size(max = 255)
        String address,

        @Size(max = 20)
        String phone,

        @Size(max = 500)
        String description,

        LocalTime openingTime,

        LocalTime closingTime
) {
}