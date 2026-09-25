package com.example.demo.service;

import com.example.demo.dto.RestaurantCreateRequest;
import com.example.demo.dto.RestaurantResponse;
import com.example.demo.dto.RestaurantUpdateRequest;
import com.example.demo.entity.Restaurant;
import com.example.demo.exception.RestaurantNotFoundException;
import com.example.demo.repository.RestaurantRepository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class RestaurantService {
    private final RestaurantRepository restaurantRepository;

    @Transactional 
    public RestaurantResponse create(RestaurantCreateRequest request) {
        Restaurant restaurant = Restaurant.builder()
                .name(request.name())
                .address(request.address())
                .phone(request.phone())
                .description(request.description())
                .openingTime(request.openingTime())
                .closingTime(request.closingTime())
                .ownerId(request.ownerId())
                .build();

        Restaurant saved = restaurantRepository.save(restaurant);
        return RestaurantResponse.from(saved);
    }

    public RestaurantResponse getById(Long restaurantId) {
        Restaurant restaurant = findRestaurantOrThrow(restaurantId);
        return RestaurantResponse.from(restaurant);
    }

    public List<RestaurantResponse> getAll() {
        return restaurantRepository.findAll().stream()
                .map(RestaurantResponse::from)
                .toList();
    }

    @Transactional
    public RestaurantResponse update(Long restaurantId, RestaurantUpdateRequest request) {
        Restaurant restaurant = findRestaurantOrThrow(restaurantId);

        restaurant.update(
                request.name(),
                request.address(),
                request.phone(),
                request.description(),
                request.openingTime(),
                request.closingTime()
        );

        return RestaurantResponse.from(restaurant);
    }
    
    @Transactional
    public void delete(Long restaurantId) {
        Restaurant restaurant = findRestaurantOrThrow(restaurantId);
        restaurantRepository.delete(restaurant);
    }

    private Restaurant findRestaurantOrThrow(Long restaurantId) {
        return restaurantRepository.findById(restaurantId)
                .orElseThrow(() -> new RestaurantNotFoundException(restaurantId));
    }
}
