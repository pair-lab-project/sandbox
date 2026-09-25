package com.example.demo.exception;

public class RestaurantNotFoundException extends RuntimeException{
    public RestaurantNotFoundException(Long id) {
        super("존재하지 않는 매장입니다. id=" + id);
    }
}
