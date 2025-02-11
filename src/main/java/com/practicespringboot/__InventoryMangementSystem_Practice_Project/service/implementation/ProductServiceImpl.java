package com.practicespringboot.__InventoryMangementSystem_Practice_Project.service.implementation;

import com.practicespringboot.__InventoryMangementSystem_Practice_Project.dto.ProductDTO;
import com.practicespringboot.__InventoryMangementSystem_Practice_Project.dto.requestDTO.ProductRequestDTO;
import com.practicespringboot.__InventoryMangementSystem_Practice_Project.entity.Category;
import com.practicespringboot.__InventoryMangementSystem_Practice_Project.entity.Product;
import com.practicespringboot.__InventoryMangementSystem_Practice_Project.exception.CategoryNotFoundException;
import com.practicespringboot.__InventoryMangementSystem_Practice_Project.exception.ProductNotFoundException;
import com.practicespringboot.__InventoryMangementSystem_Practice_Project.repository.CategoryRepository;
import com.practicespringboot.__InventoryMangementSystem_Practice_Project.repository.ProductRepository;
import com.practicespringboot.__InventoryMangementSystem_Practice_Project.service.ProductService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public ProductServiceImpl(ProductRepository productRepository, ModelMapper modelMapper, CategoryRepository categoryRepository) {
        this.productRepository = productRepository;
        this.modelMapper = modelMapper;
        this.categoryRepository = categoryRepository;
    }

    @Override
    public ProductDTO createProduct(ProductRequestDTO productRequestDTO) {
        Product product = modelMapper.map(productRequestDTO, Product.class);
        Category category = categoryRepository.findById(productRequestDTO.getCategoryId()).orElseThrow(
                ()->new CategoryNotFoundException("Invalid Category")
        );
        product.setCategory(category);
        Product savedProduct = productRepository.save(product);
        return modelMapper.map(savedProduct, ProductDTO.class);
    }

    @Override
    public List<ProductDTO> findProductsByCategoryId(Integer id) {
        Category category = categoryRepository.findById(id).orElseThrow(
                ()-> new CategoryNotFoundException("Category not found with id: " + id)
        );
        return category.getProducts().stream().map(
                (product)-> modelMapper.map(product, ProductDTO.class)
        ).toList();
    }

    @Override
    public List<ProductDTO> getAllProducts() {
        List<Category> categories = categoryRepository.findAll();
        return categories.stream()
                .flatMap(category -> category.getProducts().stream())
                .map(product -> modelMapper.map(product, ProductDTO.class))
                .toList();
    }

    @Override
    public String deleteProductById(Integer id) {
        productRepository.deleteById(id);
        return "Product Deleted Successful.";
    }

    @Override
    public ProductDTO updateProductById(ProductRequestDTO productRequestDTO, Integer id) {
        Product product1 = productRepository.findById(id).orElseThrow(
                ()->new ProductNotFoundException("Product not found")
        );
        product1.setName(productRequestDTO.getName());
        product1.setQuantity(productRequestDTO.getQuantity());
        product1.setPrice(productRequestDTO.getPrice());
        product1.setCategory(categoryRepository.findById(productRequestDTO.getCategoryId()).orElseThrow(
                ()->new CategoryNotFoundException("Category not found")
        ));
        Product savedProduct = productRepository.save(product1);
        return modelMapper.map(savedProduct, ProductDTO.class);
    }


}
