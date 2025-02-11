package com.practicespringboot.__InventoryMangementSystem_Practice_Project.service;

import com.practicespringboot.__InventoryMangementSystem_Practice_Project.dto.UserDTO;
import com.practicespringboot.__InventoryMangementSystem_Practice_Project.dto.requestDTO.UserRequestDTO;

import java.util.List;

public interface UserService {
    UserDTO createUser(UserRequestDTO userRequestDTO);
    UserDTO getUserById(Integer id);
    List<UserDTO> getAllUsers();
    UserDTO updateUserById(Integer id, UserDTO userDTO);
    String deleteUserById(Integer id);

}
