
package com.ecommerce.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

// Getters and setters ni Lombok automatically generate chestundi.
@Getter
@Setter
public class InventoryRequest {

    // Product ID compulsory; zero or negative values allow cheyyamu.
    @NotNull(message = "Product ID is required")
    @Min(value = 1, message = "Product ID must be greater than 0")
    private Long productId;

    // Available stock compulsory; negative quantity allow cheyyamu.
    @NotNull(message = "Available quantity is required")
    @Min(value = 0, message = "Available quantity cannot be negative")
    private Integer availableQuantity;
}

