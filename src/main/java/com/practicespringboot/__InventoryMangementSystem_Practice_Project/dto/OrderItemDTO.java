package com.practicespringboot.__InventoryMangementSystem_Practice_Project.dto;

import com.practicespringboot.__InventoryMangementSystem_Practice_Project.entity.Product;
import jakarta.persistence.*;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderItemDTO {
    private Integer id;
    private Integer orderId;
    private Product product;
    private Integer quantity;
}
