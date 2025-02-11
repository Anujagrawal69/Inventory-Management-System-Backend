package com.practicespringboot.__InventoryMangementSystem_Practice_Project.repository;

import com.practicespringboot.__InventoryMangementSystem_Practice_Project.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    Optional<User> findByUsername(String username);
}
