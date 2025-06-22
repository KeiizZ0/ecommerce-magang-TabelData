package com.example.ecommerce.service;

import com.example.ecommerce.entity.*;
import com.example.ecommerce.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.UUID;

@Service
public class OrderService {
    
    @Autowired
    private OrderRepository orderRepository;
    
    @Autowired
    private OrderItemRepository orderItemRepository;
    
    @Autowired
    private CartRepository cartRepository;
    
    @Autowired
    private UserRepository userRepository;
    
    @Autowired
    private ProductRepository productRepository;

    public List<Order> getOrdersByUserId(Long userId) {
        return orderRepository.findByUserId(userId);
    }

    public List<Order> getOrdersByStoreId(Long storeId) {
        return orderRepository.findByStoreId(storeId);
    }

    public Order getOrderById(Long id) {
        return orderRepository.findById(id).orElse(null);
    }

    @Transactional
    public Order createOrderFromCart(Long userId) {
        // Validate user exists
        User user = userRepository.findById(userId).orElse(null);
        if (user == null) {
            return null;
        }

        // Get cart items
        List<Cart> cartItems = cartRepository.findByUserId(userId);
        if (cartItems.isEmpty()) {
            return null;
        }

        // Prepare order
        Order order = new Order();
        order.setUser(user);
        order.setStore(cartItems.get(0).getProduct().getStore());
        order.setInvoiceNumber(generateInvoiceNumber());
        order.setStatus("PENDING");
        order.setShippingInfo("{}");
        order.setTotalAmount(0.0);

        // Save initial order to generate ID
        order = orderRepository.save(order);

        // Process cart items
        double totalAmount = processCartItems(cartItems, order);
        
        // Update order with final amount
        order.setTotalAmount(totalAmount);
        return orderRepository.save(order);
    }

    public Order updateOrderStatus(Long orderId, String status) {
        Order order = orderRepository.findById(orderId).orElse(null);
        if (order != null && isValidStatusTransition(order.getStatus(), status)) {
            order.setStatus(status);
            return orderRepository.save(order);
        }
        return null;
    }

    // Helper methods
    private String generateInvoiceNumber() {
        return "INV-" + UUID.randomUUID().toString().substring(0, 8).toUpperCase();
    }

    private double processCartItems(List<Cart> cartItems, Order order) {
        double totalAmount = 0;
        
        for (Cart cartItem : cartItems) {
            Product product = productRepository.findById(cartItem.getProduct().getId()).orElse(null);
            if (product == null) continue;
            
            OrderItem orderItem = new OrderItem();
            orderItem.setOrder(order);
            orderItem.setProduct(product);
            orderItem.setProductName(product.getName());
            orderItem.setPrice(product.getPrice());
            orderItem.setQuantity(cartItem.getQuantity());
            orderItem.setVariantInfo(cartItem.getNotes() != null ? cartItem.getNotes() : "{}");
            
            orderItemRepository.save(orderItem);
            totalAmount += product.getPrice() * cartItem.getQuantity();
            
            // Update product stock
            product.setStock(product.getStock() - cartItem.getQuantity());
            productRepository.save(product);
        }
        
        // Clear cart
        cartRepository.deleteByUserId(order.getUser().getId());
        
        return totalAmount;
    }

    private boolean isValidStatusTransition(String currentStatus, String newStatus) {
        // Implement your status transition logic here
        // Example:
        return !currentStatus.equals("CANCELLED") && 
               !currentStatus.equals("COMPLETED");
    }
}