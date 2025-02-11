package com.practicespringboot.__InventoryMangementSystem_Practice_Project.dto;

import com.practicespringboot.__InventoryMangementSystem_Practice_Project.entity.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {
    private Integer id;
    private String username;
    private Set<String> roles;
}
