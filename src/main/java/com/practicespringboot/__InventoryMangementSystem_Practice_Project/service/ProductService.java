package com.practicespringboot.__InventoryMangementSystem_Practice_Project.service;

import com.practicespringboot.__InventoryMangementSystem_Practice_Project.dto.ProductDTO;
import com.practicespringboot.__InventoryMangementSystem_Practice_Project.dto.requestDTO.ProductRequestDTO;
import com.practicespringboot.__InventoryMangementSystem_Practice_Project.entity.Product;
import com.practicespringboot.__InventoryMangementSystem_Practice_Project.repository.ProductRepository;

import java.util.List;

public interface ProductService {
    ProductDTO createProduct(ProductRequestDTO productRequestDTO);
    List<ProductDTO> findProductsByCategoryId(Integer id);
    List<ProductDTO> getAllProducts();
    String deleteProductById(Integer id);
    ProductDTO updateProductById(ProductRequestDTO productRequestDTO, Integer id);
}
