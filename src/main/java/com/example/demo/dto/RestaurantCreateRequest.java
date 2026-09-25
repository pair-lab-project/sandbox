package com.example.demo.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.LocalTime;

public record RestaurantCreateRequest(
        @NotBlank @Size(max = 100)
        String name,

        @NotBlank @Size(max = 255)
        String address,

        @Size(max = 20)
        String phone,

        @Size(max = 500)
        String description,

        @NotNull
        LocalTime openingTime,

        @NotNull
        LocalTime closingTime,

        @NotNull
        Long ownerId
) {
}