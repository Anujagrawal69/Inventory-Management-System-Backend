package com.practicespringboot.__InventoryMangementSystem_Practice_Project.controller;

import com.practicespringboot.__InventoryMangementSystem_Practice_Project.entity.Order;
import com.practicespringboot.__InventoryMangementSystem_Practice_Project.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/ims/orders")
public class OrderController{
    @Autowired
    private OrderService orderService;

    @PostMapping("/create-order")
    @PreAuthorize("hasAnyRole('ADMIN', 'MANAGER')")
    public ResponseEntity<Order> createOrder(@RequestBody Order order) {
        Order createdOrder = orderService.createOrder(order);
        return ResponseEntity.ok(createdOrder);
    }

    @GetMapping
    @PreAuthorize("hasAnyRole('ADMIN', 'MANAGER', 'INTERN')")
    public ResponseEntity<List<Order>> getAllOrders(){
        return ResponseEntity.ok(orderService.getAllOrders());
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN', 'MANAGER', 'INTERN')")
    public ResponseEntity<Order> findOrderById(@PathVariable Integer id){
        return ResponseEntity.ok(orderService.getOrderById(id));
    }

    @DeleteMapping("/delete-order/{id}")
    @PreAuthorize("hasAnyRole('ADMIN', 'MANAGER')")
    public ResponseEntity<String> deleteOrderById(@PathVariable Integer id){
        return ResponseEntity.ok(orderService.deleteOrder(id));
    }
}

