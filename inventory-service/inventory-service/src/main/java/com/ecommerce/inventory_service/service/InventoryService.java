
package com.ecommerce.inventory_service.service;

import com.ecommerce.dto.InventoryRequest;
import com.ecommerce.inventory_service.entity.Inventory;
import com.ecommerce.inventory_service.repository.InventoryRepository;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
public class InventoryService {

    private final InventoryRepository inventoryRepository;

    // Repository ni inject chestunnam; database operations kosam.
    public InventoryService(InventoryRepository inventoryRepository) {
        this.inventoryRepository = inventoryRepository;
    }

    // Product stock ni database lo create chestundi.
    @Transactional
    public Inventory createInventory(InventoryRequest request) {

        // Ee product ki inventory already undho check chestunnam.
        if (inventoryRepository.findByProductId(request.getProductId()).isPresent()) {
            throw new IllegalArgumentException(
                "Inventory already exists for this product"
            );
        }

        // Request data tho new inventory record create chestunnam.
        Inventory inventory = new Inventory();
        inventory.setProductId(request.getProductId());
        inventory.setAvailableQuantity(request.getAvailableQuantity());

        // Starting lo reserved stock zero.
        inventory.setReservedQuantity(0);

        // Stock record last update time save chestunnam.
        inventory.setUpdatedAt(LocalDateTime.now());

        // Record ni MySQL database lo save chesi return chestunnam.
        return inventoryRepository.save(inventory);
    }
    
    // Reserves stock when a customer places an order.
    @Transactional
    public Inventory reserveStock(Long productId, Integer quantity) {

        // Reject invalid quantities such as zero or negative numbers.
        if (quantity == null || quantity <= 0) {
            throw new IllegalArgumentException(
                "Reservation quantity must be greater than zero"
            );
        }

        // Lock this product's database row during the transaction.
        Inventory inventory = inventoryRepository
                .findByProductIdForUpdate(productId)
                .orElseThrow(() -> new IllegalArgumentException(
                    "Inventory not found for product: " + productId
                ));

        // Check whether enough stock is available.
        if (inventory.getAvailableQuantity() < quantity) {
            throw new IllegalArgumentException(
                "Insufficient stock available"
            );
        }

        // Move the requested quantity from available to reserved stock.
        inventory.setAvailableQuantity(
            inventory.getAvailableQuantity() - quantity
        );
        inventory.setReservedQuantity(
            inventory.getReservedQuantity() + quantity
        );

        // Update the last-modified timestamp.
        inventory.setUpdatedAt(LocalDateTime.now());

        // Save and return the updated inventory.
        return inventoryRepository.save(inventory);
    }

        // Order cancel ayina leda payment fail ayina reserved stock release chestundi.
    @Transactional
    public Inventory releaseStock(Long productId, Integer quantity) {

        // Invalid quantity ni reject chestunnam.
        if (quantity == null || quantity <= 0) {
            throw new IllegalArgumentException(
                "Release quantity must be greater than zero"
            );
        }

        // Concurrent requests valla stock mismatch kakunda row lock chestunnam.
        Inventory inventory = inventoryRepository
                .findByProductIdForUpdate(productId)
                .orElseThrow(() -> new IllegalArgumentException(
                    "Inventory not found for product: " + productId
                ));

        // Reserve chesina quantity kanna ekkuva release cheyyakudadhu.
        if (inventory.getReservedQuantity() < quantity) {
            throw new IllegalArgumentException(
                "Release quantity exceeds reserved stock"
            );
        }

        // Reserved stock nunchi quantity taggistunnam.
        inventory.setReservedQuantity(
            inventory.getReservedQuantity() - quantity
        );

        // Aa quantity ni available stock ki add chestunnam.
        inventory.setAvailableQuantity(
            inventory.getAvailableQuantity() + quantity
        );

        // Last update time save chestunnam.
        inventory.setUpdatedAt(LocalDateTime.now());

        // Updated stock database lo save chestunnam.
        return inventoryRepository.save(inventory);
    }

    
}
