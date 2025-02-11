package com.practicespringboot.__InventoryMangementSystem_Practice_Project.service.implementation;

import com.practicespringboot.__InventoryMangementSystem_Practice_Project.dto.RoleDTO;
import com.practicespringboot.__InventoryMangementSystem_Practice_Project.entity.Role;
import com.practicespringboot.__InventoryMangementSystem_Practice_Project.exception.RoleNotFoundException;
import com.practicespringboot.__InventoryMangementSystem_Practice_Project.repository.RoleRepository;
import com.practicespringboot.__InventoryMangementSystem_Practice_Project.repository.UserRepository;
import com.practicespringboot.__InventoryMangementSystem_Practice_Project.service.RoleService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {
    private final ModelMapper modelMapper;
    private final RoleRepository roleRepository;

    @Autowired
    RoleServiceImpl(ModelMapper modelMapper, PasswordEncoder passwordEncoder, RoleRepository roleRepository){
        this.roleRepository = roleRepository;
        this.modelMapper = modelMapper;
    }
    @Override
    public RoleDTO getRoleByName(String name) {
        Role role = roleRepository.findRoleByName(name).orElseThrow(
                ()-> new RoleNotFoundException("Role not found with name: " + name)
        );
        return modelMapper.map(role, RoleDTO.class);
    }

    @Override
    public List<RoleDTO> getAllRoles() {
        List<Role> roles = roleRepository.findAll();
        return roles.stream().map(
                (role)->modelMapper.map(role, RoleDTO.class)
        ).toList();
    }


    @Override
    public RoleDTO createRole(RoleDTO roleDTO) {
        Role role = modelMapper.map(roleDTO, Role.class);
        role.setName(roleDTO.getName());
        Role savedRole = roleRepository.save(role);
        return modelMapper.map(savedRole, RoleDTO.class);
    }

    @Override
    public String deleteRoleById(Integer id) {
        roleRepository.deleteById(id);
        return "Role Deleted Successful.";
    }
}
