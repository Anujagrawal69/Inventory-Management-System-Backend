package com.practicespringboot.__InventoryMangementSystem_Practice_Project.repository;

import com.practicespringboot.__InventoryMangementSystem_Practice_Project.entity.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface OrderItemsRepository extends JpaRepository<OrderItem, Integer> {
    @Transactional
    @Modifying
    @Query("DELETE FROM OrderItem oi WHERE oi.orderId = :orderId")
    void deleteByOrderId(@Param("orderId") Integer orderId);
}
