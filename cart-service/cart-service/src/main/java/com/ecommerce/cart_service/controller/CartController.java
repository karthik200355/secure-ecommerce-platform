
package com.ecommerce.cart_service.controller;

import com.ecommerce.cart_service.entity.CartItem;
import com.ecommerce.cart_service.service.CartService;

import jakarta.validation.Valid; // Request body validation activate chesthundi
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

// REST API requests ni receive chese controller.
@RestController
@RequestMapping("/api/cart")
public class CartController {

    private final CartService cartService;

    // Spring automatically CartService object ni inject chesthundi.
    public CartController(CartService cartService) {
        this.cartService = cartService;
    }

    // GET /api/cart/1 -> user ID 1 cart items retrieve chesthundi.
    @GetMapping("/{userId}")
    public List<CartItem> getCartItems(@PathVariable Long userId) {
        return cartService.getCartItems(userId);
    }

    // POST /api/cart -> cart lo item add chesthundi.
    @PostMapping
    public CartItem addToCart(@Valid @RequestBody CartItem cartItem) {
        return cartService.addToCart(cartItem);
    }

    // DELETE /api/cart/items/1 -> cart item ID 1 ni delete chesthundi.
    @DeleteMapping("/items/{itemId}")
    public void removeCartItem(@PathVariable Long itemId) {
        cartService.removeCartItem(itemId);
    }
}
