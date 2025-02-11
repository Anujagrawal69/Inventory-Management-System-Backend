package com.practicespringboot.__InventoryMangementSystem_Practice_Project.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "order_items")
public class OrderItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private Integer orderId;

    @ManyToOne
    @JoinColumn(name = "product_id", nullable = false, referencedColumnName = "id")
    private Product product;

    @Column(nullable = false)
    private Integer quantity;
}
