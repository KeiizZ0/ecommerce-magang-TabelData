package com.example.ecommerce.controller;

import com.example.ecommerce.entity.OrderItem;
import com.example.ecommerce.service.OrderItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/orders/{orderId}/items")
public class OrderItemController {
    @Autowired
    private OrderItemService orderItemService;

    @GetMapping
    public List<OrderItem> getOrderItems(@PathVariable Long orderId) {
        return orderItemService.getOrderItemsByOrderId(orderId);
    }

    @GetMapping("/{itemId}")
    public ResponseEntity<OrderItem> getOrderItem(
            @PathVariable Long orderId,
            @PathVariable Long itemId) {
        OrderItem item = orderItemService.getOrderItemById(itemId);
        return item != null ? ResponseEntity.ok(item) : ResponseEntity.notFound().build();
    }

    @PostMapping
    public OrderItem addOrderItem(
            @PathVariable Long orderId,
            @RequestBody OrderItem orderItem) {
        return orderItemService.createOrderItem(orderItem);
    }

    @PutMapping("/{itemId}")
    public ResponseEntity<OrderItem> updateOrderItem(
            @PathVariable Long orderId,
            @PathVariable Long itemId,
            @RequestBody OrderItem itemDetails) {
        OrderItem updatedItem = orderItemService.updateOrderItem(itemId, itemDetails);
        return updatedItem != null ? ResponseEntity.ok(updatedItem) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{itemId}")
    public ResponseEntity<Void> deleteOrderItem(
            @PathVariable Long orderId,
            @PathVariable Long itemId) {
        orderItemService.deleteOrderItem(itemId);
        return ResponseEntity.noContent().build();
    }
}