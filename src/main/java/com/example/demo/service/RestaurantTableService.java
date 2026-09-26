package com.example.demo.service;

import com.example.demo.dto.RestaurantTableCreateRequest;
import com.example.demo.dto.RestaurantTableResponse;
import com.example.demo.dto.RestaurantTableUpdateRequest;
import com.example.demo.entity.Restaurant;
import com.example.demo.entity.RestaurantTable;
import com.example.demo.exception.RestaurantNotFoundException;
import com.example.demo.exception.RestaurantTableNotFoundException;
import com.example.demo.repository.RestaurantRepository;
import com.example.demo.repository.RestaurantTableRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class RestaurantTableService {
    private final RestaurantTableRepository restaurantTableRepository;
    private final RestaurantRepository restaurantRepository;

    @Transactional
    public RestaurantTableResponse create(Long restaurantId, RestaurantTableCreateRequest request) {
        Restaurant restaurant = restaurantRepository.findById(restaurantId)
                .orElseThrow(() -> new RestaurantNotFoundException(restaurantId));

        RestaurantTable table = RestaurantTable.builder()
                .restaurant(restaurant)
                .tableNumber(request.tableNumber())
                .capacity(request.capacity())
                .build();

        RestaurantTable saved = restaurantTableRepository.save(table);
        return RestaurantTableResponse.from(saved);
    }

    public List<RestaurantTableResponse> getByRestaurant(Long restaurantId) {
        if (!restaurantRepository.existsById(restaurantId)) {
            throw new RestaurantNotFoundException(restaurantId);
        }

        return restaurantTableRepository.findByRestaurantId(restaurantId).stream()
                .map(RestaurantTableResponse::from)
                .toList();
    }

    @Transactional
    public RestaurantTableResponse update(Long tableId, RestaurantTableUpdateRequest request) {
        RestaurantTable table = findTableOrThrow(tableId);

        table.update(request.tableNumber(), request.capacity());

        return RestaurantTableResponse.from(table);
    }

    @Transactional
    public void delete(Long tableId) {
        RestaurantTable table = findTableOrThrow(tableId);
        restaurantTableRepository.delete(table);
    }

    private RestaurantTable findTableOrThrow(Long tableId) {
        return restaurantTableRepository.findById(tableId)
                .orElseThrow(() -> new RestaurantTableNotFoundException(tableId));
    }
}
