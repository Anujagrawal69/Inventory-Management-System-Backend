package com.practicespringboot.__InventoryMangementSystem_Practice_Project.exception;

public class ProductNotFoundException extends RuntimeException{
    public ProductNotFoundException(String message){
        super(message);
    }
}
