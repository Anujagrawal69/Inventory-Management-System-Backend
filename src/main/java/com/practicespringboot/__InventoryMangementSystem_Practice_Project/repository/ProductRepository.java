package com.practicespringboot.__InventoryMangementSystem_Practice_Project.repository;

import com.practicespringboot.__InventoryMangementSystem_Practice_Project.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {
    List<Product> findByCategoryId(Integer categoryId);
//    List<Product> findByPriceBetween(Integer minPrice, Integer maxPrice);
}
