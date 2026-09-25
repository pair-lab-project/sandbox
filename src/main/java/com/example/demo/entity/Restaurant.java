package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.time.LocalTime;


@Entity
@Table(name="restaurant")
@Getter
@NoArgsConstructor(access=AccessLevel.PROTECTED)
public class Restaurant{
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id; 

    @Column(nullable = false, length = 100)
    private String name;

    @Column(nullable = false, length = 255)
    private String address;

    @Column(length = 20)
    private String phone;

    @Column(length = 500)
    private String description;

    @Column(name = "opening_time", nullable = false)
    private LocalTime openingTime;

    @Column(name = "closing_time", nullable = false)
    private LocalTime closingTime;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private RestaurantStatus status;

    @Column(name = "owner_id", nullable = false)
    private Long ownerId;

    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at", nullable = false)
    private LocalDateTime updatedAt;

    @Builder
    public Restaurant(String name, String address, String phone, String description,
                       LocalTime openingTime, LocalTime closingTime, Long ownerId) {
        this.name = name;
        this.address = address;
        this.phone = phone;
        this.description = description;
        this.openingTime = openingTime;
        this.closingTime = closingTime;
        this.ownerId = ownerId;
        this.status = RestaurantStatus.OPEN;
    }

    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        this.updatedAt = LocalDateTime.now();
    }

    public void update(String name, String address, String phone, String description,
                        LocalTime openingTime, LocalTime closingTime) {
        if (name != null) this.name = name;
        if (address != null) this.address = address;
        if (phone != null) this.phone = phone;
        if (description != null) this.description = description;
        if (openingTime != null) this.openingTime = openingTime;
        if (closingTime != null) this.closingTime = closingTime;
    }

    public void close() {
        this.status = RestaurantStatus.CLOSED;
    }

    public void open() {
        this.status = RestaurantStatus.OPEN;
    }
}
