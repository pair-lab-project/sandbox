package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name="restaurant_table")
@Getter
@NoArgsConstructor(access=AccessLevel.PROTECTED)
public class RestaurantTable {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="restaurant_id", nullable=false)
    private Restaurant restaurant;

    @Column(name="table_number", nullable=false)
    private Integer tableNumber;

    @Column(nullable=false)
    private Integer capacity;

    @Enumerated(EnumType.STRING)
    @Column(nullable=false, length=20)
    private TableStatus status;

    @Column(name="created_at", nullable=false, updatable=false)
    private LocalDateTime createdAt;

    @Column(name="updated_at", nullable=false)
    private LocalDateTime updatedAt;

    @Builder 
    public RestaurantTable(Restaurant restaurant, Integer tableNumber, Integer capacity) {
        this.restaurant = restaurant;
        this.tableNumber = tableNumber;
        this.capacity = capacity;
        this.status = TableStatus.ACTIVE;
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
 
    public void update(Integer tableNumber, Integer capacity) {
        if (tableNumber != null) this.tableNumber = tableNumber;
        if (capacity != null) this.capacity = capacity;
    }

    public void activate() {
        this.status = TableStatus.ACTIVE;
    }

    public void deactivate() {
        this.status = TableStatus.INACTIVE;
    }
}
