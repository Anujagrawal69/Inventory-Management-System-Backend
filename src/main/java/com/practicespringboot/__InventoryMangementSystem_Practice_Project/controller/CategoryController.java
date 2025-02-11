package com.practicespringboot.__InventoryMangementSystem_Practice_Project.controller;

import com.practicespringboot.__InventoryMangementSystem_Practice_Project.dto.CategoryDTO;
import com.practicespringboot.__InventoryMangementSystem_Practice_Project.service.CategoryService;
import org.apache.catalina.connector.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/ims/categories")
public class CategoryController {
    @Autowired
    CategoryService categoryService;

    @PostMapping("/create-category")
    @PreAuthorize("hasAnyRole('ADMIN', 'MANAGER')")
    public ResponseEntity<CategoryDTO> createCategory(@RequestBody CategoryDTO categoryDTO){
        return new ResponseEntity<>(categoryService.createCategory(categoryDTO), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN', 'MANAGER', 'INTERN')")
    public ResponseEntity<CategoryDTO> getCategoryById(@PathVariable Integer id){
        return ResponseEntity.ok(categoryService.getCategoryById(id));
    }

    @GetMapping
    @PreAuthorize("hasAnyRole('ADMIN', 'MANAGER', 'INTERN')")
    public ResponseEntity<List<CategoryDTO>> getAllCategories(){
        return ResponseEntity.ok(categoryService.getAllCategories());
    }

    @PutMapping("/update-category/{id}")
    @PreAuthorize("hasAnyRole('ADMIN', 'MANAGER', 'INTERN')")
    public ResponseEntity<CategoryDTO> updateCategoryById(
            @RequestBody CategoryDTO categoryDTO,
            @PathVariable Integer id){
        return ResponseEntity.ok(categoryService.updateCategoryById(categoryDTO, id));
    }

    @DeleteMapping("/delete-category/{id}")
    @PreAuthorize("hasAnyRole('ADMIN', 'MANAGER')")
    public ResponseEntity<String> deleteCategoryById(@PathVariable Integer id){
        return ResponseEntity.ok(categoryService.deleteCategoryById(id));
    }
}
