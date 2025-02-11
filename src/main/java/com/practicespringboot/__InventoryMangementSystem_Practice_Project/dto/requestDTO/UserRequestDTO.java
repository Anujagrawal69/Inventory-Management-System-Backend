package com.practicespringboot.__InventoryMangementSystem_Practice_Project.dto.requestDTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserRequestDTO {
    private Integer id;
    private String username;
    private String password;
    private Set<String> roles;
}
