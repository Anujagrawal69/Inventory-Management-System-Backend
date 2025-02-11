package com.practicespringboot.__InventoryMangementSystem_Practice_Project.service.implementation;

import com.practicespringboot.__InventoryMangementSystem_Practice_Project.entity.Order;
import com.practicespringboot.__InventoryMangementSystem_Practice_Project.entity.OrderItem;
import com.practicespringboot.__InventoryMangementSystem_Practice_Project.entity.Product;
import com.practicespringboot.__InventoryMangementSystem_Practice_Project.exception.OrderNotFoundException;
import com.practicespringboot.__InventoryMangementSystem_Practice_Project.repository.OrderItemsRepository;
import com.practicespringboot.__InventoryMangementSystem_Practice_Project.repository.OrderRepository;
import com.practicespringboot.__InventoryMangementSystem_Practice_Project.repository.ProductRepository;
import com.practicespringboot.__InventoryMangementSystem_Practice_Project.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private OrderItemsRepository orderItemRepository;

    @Autowired
    private ProductRepository productRepository;

    @Override
    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    @Override
    public Order getOrderById(Integer id) {
        return orderRepository.findById(id).orElseThrow(
                ()-> new OrderNotFoundException("Order not found with Id: " + id)
        );
    }

    @Override
    @Transactional
    public Order createOrder(Order order) {
        Order savedOrder = orderRepository.save(order);
        if (order.getOrderItems() != null && !order.getOrderItems().isEmpty()) {
            for (OrderItem item : order.getOrderItems()) {
                Product product = productRepository.findById(item.getProduct().getId())
                        .orElseThrow(() -> new RuntimeException("Product not found with ID: " + item.getProduct().getId()));

                item.setOrderId(savedOrder.getId());
                item.setProduct(product);
                orderItemRepository.save(item);
            }
        }
        return savedOrder;
    }

    @Override
    @Transactional
    public String deleteOrder(Integer id) {
        // Check if the order exists
        if (!orderRepository.existsById(id)) {
            throw new OrderNotFoundException("Order not found with ID: " + id);
        }

        // ✅ Step 1: Delete OrderItems directly in one query
        orderItemRepository.deleteByOrderId(id);

        // ✅ Step 2: Delete the Order after clearing dependencies
        orderRepository.deleteById(id);

        return "Order deleted successfully.";
    }
}