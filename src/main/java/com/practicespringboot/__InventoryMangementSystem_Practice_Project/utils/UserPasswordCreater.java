package com.practicespringboot.__InventoryMangementSystem_Practice_Project.utils;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

public class UserPasswordCreater {
    public static void main(String[] args) {
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        System.out.println(passwordEncoder.encode("rudra"));
        System.out.println(passwordEncoder.encode("rianchal"));
    }
}
