package com.practicespringboot.__InventoryMangementSystem_Practice_Project.service;

import com.practicespringboot.__InventoryMangementSystem_Practice_Project.dto.CategoryDTO;

import java.util.List;

public interface CategoryService {
    CategoryDTO createCategory(CategoryDTO categoryDTO);
    CategoryDTO getCategoryById(Integer id);
    List<CategoryDTO> getAllCategories();
    CategoryDTO updateCategoryById(CategoryDTO categoryDTO, Integer id);
    String deleteCategoryById(Integer id);
}
