package com.practicespringboot.__InventoryMangementSystem_Practice_Project.controller;

import com.practicespringboot.__InventoryMangementSystem_Practice_Project.dto.ProductDTO;
import com.practicespringboot.__InventoryMangementSystem_Practice_Project.dto.requestDTO.ProductRequestDTO;
import com.practicespringboot.__InventoryMangementSystem_Practice_Project.entity.Product;
import com.practicespringboot.__InventoryMangementSystem_Practice_Project.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/ims/products")
public class ProductController {
    ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping("/create-product")
    @PreAuthorize("hasAnyRole('ADMIN', 'MANAGER')")
    public ResponseEntity<ProductDTO> createProduct(@RequestBody ProductRequestDTO productRequestDTO){
        return new ResponseEntity<>(productService.createProduct(productRequestDTO), HttpStatus.CREATED);
    }

    @GetMapping("{id}")
    @PreAuthorize("hasAnyRole('ADMIN', 'MANAGER', 'INTERN')")
    public ResponseEntity<List<ProductDTO>> findProductsByCategoryId(@PathVariable Integer id){
        return ResponseEntity.ok(productService.findProductsByCategoryId(id));
    }

    @GetMapping
    @PreAuthorize("hasAnyRole('ADMIN', 'MANAGER', 'INTERN')")
    public ResponseEntity<List<ProductDTO>> getAllProducts(){
        return ResponseEntity.ok(productService.getAllProducts());
    }

    @DeleteMapping("/delete-product/{id}")
    @PreAuthorize("hasAnyRole('ADMIN', 'MANAGER')")
    public ResponseEntity<String> deleteProductById(@PathVariable Integer id){
        return ResponseEntity.ok(productService.deleteProductById(id));
    }

    @PutMapping("/update-product/{id}")
    @PreAuthorize("hasAnyRole('ADMIN', 'MANAGER')")
    public ResponseEntity<ProductDTO> updateProductById(
            @RequestBody ProductRequestDTO productRequestDTO,
            @PathVariable Integer id){
        return ResponseEntity.ok(productService.updateProductById(productRequestDTO,
                id));
    }
}
