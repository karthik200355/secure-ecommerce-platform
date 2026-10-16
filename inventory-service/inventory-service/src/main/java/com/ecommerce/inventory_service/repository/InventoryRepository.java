
package com.ecommerce.inventory_service.repository;

import com.ecommerce.inventory_service.entity.Inventory;

import jakarta.persistence.LockModeType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

// Provides database operations for inventory records.
public interface InventoryRepository extends JpaRepository<Inventory, Long> {

    // Finds inventory normally using the product ID.
    Optional<Inventory> findByProductId(Long productId);

    // Locks the matching inventory row while a transaction is running.
    // Another transaction must wait before modifying the same product's stock.
    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @Query("SELECT i FROM Inventory i WHERE i.productId = :productId")
    Optional<Inventory> findByProductIdForUpdate(
            @Param("productId") Long productId
    );
}
