package com.practicespringboot.__InventoryMangementSystem_Practice_Project.exception;

public class OrderNotFoundException extends RuntimeException{
    public OrderNotFoundException(String message){
        super(message);
    }
}
