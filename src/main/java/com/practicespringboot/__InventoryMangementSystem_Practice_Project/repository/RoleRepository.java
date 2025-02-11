package com.practicespringboot.__InventoryMangementSystem_Practice_Project.repository;

import com.practicespringboot.__InventoryMangementSystem_Practice_Project.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Integer> {
    Optional<Role> findRoleByName(String role);
}
