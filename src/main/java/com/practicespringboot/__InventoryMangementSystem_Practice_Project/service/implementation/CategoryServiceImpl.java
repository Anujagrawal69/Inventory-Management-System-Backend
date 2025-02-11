package com.practicespringboot.__InventoryMangementSystem_Practice_Project.service.implementation;

import com.practicespringboot.__InventoryMangementSystem_Practice_Project.dto.CategoryDTO;
import com.practicespringboot.__InventoryMangementSystem_Practice_Project.entity.Category;
import com.practicespringboot.__InventoryMangementSystem_Practice_Project.exception.CategoryNotFoundException;
import com.practicespringboot.__InventoryMangementSystem_Practice_Project.repository.CategoryRepository;
import com.practicespringboot.__InventoryMangementSystem_Practice_Project.service.CategoryService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {
    CategoryRepository categoryRepository;
    ModelMapper modelMapper;

    @Autowired
    public CategoryServiceImpl(CategoryRepository categoryRepository, ModelMapper modelMapper) {
        this.categoryRepository = categoryRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public CategoryDTO createCategory(CategoryDTO categoryDTO) {
        Category category = modelMapper.map(categoryDTO, Category.class);
        category.setName(categoryDTO.getName());
        Category savedCategory = categoryRepository.save(category);
        return modelMapper.map(savedCategory, CategoryDTO.class);
    }

    @Override
    public CategoryDTO getCategoryById(Integer id) {
        return modelMapper.map(categoryRepository.findById(id)
                .orElseThrow(
                        ()->new CategoryNotFoundException("Category not found.")
                ), CategoryDTO.class);
    }

    @Override
    public List<CategoryDTO> getAllCategories() {
        return categoryRepository.findAll().stream().map(
                (category)-> modelMapper.map(category, CategoryDTO.class)
        ).toList();
    }

    @Override
    public CategoryDTO updateCategoryById(CategoryDTO categoryDTO, Integer id) {
        Category category = categoryRepository.findById(id).orElseThrow(
                ()-> new CategoryNotFoundException("Category not found.")
        );
        category.setName(categoryDTO.getName());
        Category savedCategory = categoryRepository.save(category);
        return modelMapper.map(savedCategory, CategoryDTO.class);
    }

    @Override
    public String deleteCategoryById(Integer id) {
        categoryRepository.deleteById(id);
        return "Category Deleted Successful.";
    }

}
