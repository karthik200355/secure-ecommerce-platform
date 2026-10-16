
package com.ecommerce.inventory_service.controller;

import com.ecommerce.dto.InventoryRequest;
import com.ecommerce.dto.StockReservationRequest;
import com.ecommerce.inventory_service.entity.Inventory;
import com.ecommerce.inventory_service.service.InventoryService;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/inventory")
public class InventoryController {

    private final InventoryService inventoryService;

    // Injects the service that handles inventory business logic.
    public InventoryController(InventoryService inventoryService) {
        this.inventoryService = inventoryService;
    }

    // Checks whether the Inventory Service is running.
    @GetMapping("/health")
    public String healthCheck() {
        return "Inventory Service is running!";
    }

    // Creates an initial stock record for a product.
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Inventory createInventory(
            @Valid @RequestBody InventoryRequest request) {
        return inventoryService.createInventory(request);
    }

    // Reserves stock for an order.
    @PostMapping("/reserve")
    public Inventory reserveStock(
            @Valid @RequestBody StockReservationRequest request) {

        // Calls the transactional service method to lock and update stock.
        return inventoryService.reserveStock(
                request.getProductId(),
                request.getQuantity()
        );
    }
}
