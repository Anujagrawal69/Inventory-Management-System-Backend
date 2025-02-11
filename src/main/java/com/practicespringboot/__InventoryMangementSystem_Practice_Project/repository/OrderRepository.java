package com.practicespringboot.__InventoryMangementSystem_Practice_Project.repository;

import com.practicespringboot.__InventoryMangementSystem_Practice_Project.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Integer> {
    List<Order> findByUserId(Integer id);
    List<Order> findByOrderDateBetween(LocalDateTime startDate, LocalDateTime endDate);
}
