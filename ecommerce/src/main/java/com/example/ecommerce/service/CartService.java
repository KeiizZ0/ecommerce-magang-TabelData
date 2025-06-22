package com.example.ecommerce.service;

import com.example.ecommerce.entity.Cart;
import com.example.ecommerce.repository.CartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CartService {
    @Autowired
    private CartRepository cartRepository;

    public List<Cart> getCartByUserId(Long userId) {
        return cartRepository.findByUserId(userId);
    }

    public Cart addToCart(Cart cart) {
        // Cek apakah produk sudah ada di keranjang
        Cart existingCart = cartRepository.findByUserIdAndProductId(
            cart.getUser().getId(), 
            cart.getProduct().getId()
        );
        
        if (existingCart != null) {
            existingCart.setQuantity(existingCart.getQuantity() + cart.getQuantity());
            return cartRepository.save(existingCart);
        } else {
            return cartRepository.save(cart);
        }
    }

    public Cart updateCartItem(Long cartId, Integer quantity) {
        Cart cart = cartRepository.findById(cartId).orElse(null);
        if (cart != null) {
            cart.setQuantity(quantity);
            return cartRepository.save(cart);
        }
        return null;
    }

    public void removeFromCart(Long cartId) {
        cartRepository.deleteById(cartId);
    }

    public void clearUserCart(Long userId) {
        cartRepository.deleteByUserId(userId);
    }
}