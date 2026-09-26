package com.example.demo.exception;

public class RestaurantTableNotFoundException extends RuntimeException{
    public RestaurantTableNotFoundException(Long id) {
        super("존재하지 않는 테이블입니다. id=" + id);
    }
}
