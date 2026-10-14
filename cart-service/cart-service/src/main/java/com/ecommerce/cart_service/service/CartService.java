package com.ecommerce.cart_service.service;

import com.ecommerce.cart_service.entity.CartItem;

import com.ecommerce.cart_service.repository.CartItemRepository;

import org.springframework.stereotype.Service;

import java.util.List;

@Service //ee classs buisness logic handle chesthadi

public class CartService {
    private final CartItemRepository cartItemRepository;

    //repository ni spring automatic ga inject chesthadi
    public CartService(CartItemRepository cartItemRepository){
        this.cartItemRepository = cartItemRepository;
    }

    //user cart lo unna item retrive cheyataniki
    public List<CartItem> getCartItems(Long userId){
        return cartItemRepository.findByUserId(userId);
    }

    //kotha cart item ni database lo save chesthadi
   public CartItem addToCart(CartItem cartItem) {
    return cartItemRepository
            .findByUserIdAndProductId(
                    cartItem.getUserId(),
                    cartItem.getProductId()
            )
            .map(existingItem -> {
                existingItem.setQuantity(
                        existingItem.getQuantity() + cartItem.getQuantity()
                );
                return cartItemRepository.save(existingItem);
            })
            .orElseGet(() -> cartItemRepository.save(cartItem));
}

    //cart item ni remove chesthadi;item id use chestham
    public void removeCartItem(Long itemId){
        cartItemRepository.deleteById(itemId);
    }
}

