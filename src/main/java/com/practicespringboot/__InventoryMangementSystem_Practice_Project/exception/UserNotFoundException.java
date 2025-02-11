package com.practicespringboot.__InventoryMangementSystem_Practice_Project.exception;

public class UserNotFoundException extends RuntimeException{
    public UserNotFoundException(String message){
        super(message);
    }
}
