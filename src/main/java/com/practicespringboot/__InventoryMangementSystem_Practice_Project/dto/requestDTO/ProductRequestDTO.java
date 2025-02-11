package com.practicespringboot.__InventoryMangementSystem_Practice_Project.dto.requestDTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductRequestDTO {
    private Integer id;
    private String name;
    private double price;
    private Integer quantity;
    private Integer categoryId;
}
