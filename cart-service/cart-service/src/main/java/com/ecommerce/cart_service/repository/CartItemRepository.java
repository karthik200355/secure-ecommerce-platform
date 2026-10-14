
package com.ecommerce.cart_service.repository;

// CartItem entity ni import chestunnam
import com.ecommerce.cart_service.entity.CartItem;

// Spring Data JPA repository interface
import org.springframework.data.jpa.repository.JpaRepository;

// Multiple cart items retrieve cheyadaniki List use chestham
import java.util.List;

// Item dorikithe value, lekapothe empty result ivvadaniki Optional use chestham
import java.util.Optional;

// JpaRepository valla database CRUD operations automatic ga vastayi
public interface CartItemRepository extends JpaRepository<CartItem, Long> {

    // Oka user cart lo unna items anni retrieve chesthundi
    // Example: userId = 1 ante user 1 cart items list vasthundi
    List<CartItem> findByUserId(Long userId);

    // Oka user cart lo specific product already undho check chesthundi
    // User ID mariyu Product ID rendu match avvali
    // Product dorikithe CartItem, dorakakapothe empty Optional vasthundi
    Optional<CartItem> findByUserIdAndProductId(Long userId, Long productId);
}
