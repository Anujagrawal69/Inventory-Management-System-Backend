package com.practicespringboot.__InventoryMangementSystem_Practice_Project.controller;

import com.practicespringboot.__InventoryMangementSystem_Practice_Project.dto.UserDTO;
import com.practicespringboot.__InventoryMangementSystem_Practice_Project.dto.requestDTO.UserRequestDTO;
import com.practicespringboot.__InventoryMangementSystem_Practice_Project.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/ims/users")
public class UserController {
    @Autowired
    UserService userService;

    @PostMapping("/create-user")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<UserDTO> createUser(@RequestBody UserRequestDTO userRequestDTO){
        UserDTO userDTO = userService.createUser(userRequestDTO);
        return new ResponseEntity<>(userDTO, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN', 'MANAGER', 'INTERN')")
    public ResponseEntity<UserDTO> getUserById(@PathVariable Integer id){
        UserDTO userDTO = userService.getUserById(id);
        return ResponseEntity.ok(userDTO);
    }

    @GetMapping
    @PreAuthorize("hasAnyRole('ADMIN', 'MANAGER', 'INTERN')")
    public ResponseEntity<List<UserDTO>> getAllUsers(){
        return ResponseEntity.ok(userService.getAllUsers());
    }

    @PutMapping("/update-user/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<UserDTO> updateUserById(@PathVariable Integer id, @RequestBody UserDTO userDTO){
        return ResponseEntity.ok(userService.updateUserById(id, userDTO));
    }

    @DeleteMapping("/delete-user/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<String> deleteUserById(@PathVariable Integer id){
        return ResponseEntity.ok(userService.deleteUserById(id));
    }
}
