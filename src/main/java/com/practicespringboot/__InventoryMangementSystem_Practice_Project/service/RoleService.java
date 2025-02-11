package com.practicespringboot.__InventoryMangementSystem_Practice_Project.service;

import com.practicespringboot.__InventoryMangementSystem_Practice_Project.dto.RoleDTO;

import java.util.List;

public interface RoleService {

    RoleDTO getRoleByName(String name);
    List<RoleDTO> getAllRoles();
    RoleDTO createRole(RoleDTO roleDTO);
    String deleteRoleById(Integer id);
}
