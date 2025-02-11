package com.practicespringboot.__InventoryMangementSystem_Practice_Project.service;

import com.practicespringboot.__InventoryMangementSystem_Practice_Project.entity.Order;

import java.util.List;

public interface OrderService {
    List<Order> getAllOrders();
    Order getOrderById(Integer id);
    Order createOrder(Order order);
//    Order updateOrder(Integer id, Order order);
    String deleteOrder(Integer id);
}
