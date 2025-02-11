package com.practicespringboot.__InventoryMangementSystem_Practice_Project.controller;

import com.practicespringboot.__InventoryMangementSystem_Practice_Project.dto.RoleDTO;
import com.practicespringboot.__InventoryMangementSystem_Practice_Project.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/ims/roles")
public class RoleController {
    @Autowired
    RoleService roleService;

    @GetMapping("/{role-name}")
    @PreAuthorize("hasAnyRole('ADMIN', 'MANAGER', 'INTERN')")
    public ResponseEntity<RoleDTO> getRoleIdByRoleName(@PathVariable("role-name") String name){
        return ResponseEntity.ok(roleService.getRoleByName(name));
    }

    @GetMapping
    @PreAuthorize("hasAnyRole('ADMIN', 'MANAGER', 'INTERN')")
    public ResponseEntity<List<RoleDTO>> getAllRoles(){
        return ResponseEntity.ok(roleService.getAllRoles());
    }

    @PostMapping("/create-role")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<RoleDTO> createRole(@RequestBody RoleDTO roleDTO){
        RoleDTO roleDTO1 = roleService.createRole(roleDTO);
        return new ResponseEntity<>(roleDTO1, HttpStatus.CREATED);
    }

    @DeleteMapping("/delete-role/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<String> deleteRoleById(@PathVariable Integer id){
        return ResponseEntity.ok(roleService.deleteRoleById(id));
    }
}
