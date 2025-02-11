package com.practicespringboot.__InventoryMangementSystem_Practice_Project.exception;


public class CategoryNotFoundException extends RuntimeException{
    public CategoryNotFoundException(String message){
        super(message);
    }
}
