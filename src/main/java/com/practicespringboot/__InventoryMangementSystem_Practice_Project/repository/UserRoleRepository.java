package com.practicespringboot.__InventoryMangementSystem_Practice_Project.repository;

import com.practicespringboot.__InventoryMangementSystem_Practice_Project.entity.UserRole;
import com.practicespringboot.__InventoryMangementSystem_Practice_Project.utils.UserRoleId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface UserRoleRepository extends JpaRepository<UserRole, UserRoleId> {
    @Modifying
    @Transactional
    @Query("DELETE FROM UserRole ur WHERE ur.user.id = :userId")
    void deleteByUserId(@Param("userId") Integer userId);
}