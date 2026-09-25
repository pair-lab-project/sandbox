package com.example.demo.controller;

import com.example.demo.dto.RestaurantCreateRequest;
import com.example.demo.dto.RestaurantResponse;
import com.example.demo.dto.RestaurantUpdateRequest;
import com.example.demo.service.RestaurantService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/restaurants")
@RequiredArgsConstructor
public class RestaurantController {
    private final RestaurantService restaurantService;

    @PostMapping
    public ResponseEntity<RestaurantResponse> create(@Valid @RequestBody RestaurantCreateRequest request) {
        RestaurantResponse response = restaurantService.create(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping
    public ResponseEntity<List<RestaurantResponse>> getAll() {
        return ResponseEntity.ok(restaurantService.getAll());
    }

    @GetMapping("/{restaurantId}")
    public ResponseEntity<RestaurantResponse> getById(@PathVariable Long restaurantId) {
        return ResponseEntity.ok(restaurantService.getById(restaurantId));
    }

    @PatchMapping("/{restaurantId}")
    public ResponseEntity<RestaurantResponse> update(
            @PathVariable Long restaurantId,
            @Valid @RequestBody RestaurantUpdateRequest request
    ) {
        return ResponseEntity.ok(restaurantService.update(restaurantId, request));
    }

    @DeleteMapping("/{restaurantId}")
    public ResponseEntity<Void> delete(@PathVariable Long restaurantId) {
        restaurantService.delete(restaurantId);
        return ResponseEntity.noContent().build();
    }
}
