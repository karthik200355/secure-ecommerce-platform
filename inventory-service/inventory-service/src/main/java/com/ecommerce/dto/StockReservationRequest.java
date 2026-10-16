
package com.ecommerce.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

// Lombok automatically generates getters and setters.
@Getter
@Setter
public class StockReservationRequest {

    // Identifies which product's stock should be reserved.
    @NotNull(message = "Product ID is required")
    @Min(value = 1, message = "Product ID must be greater than 0")
    private Long productId;

    // Number of units the customer wants to reserve.
    @NotNull(message = "Reservation quantity is required")
    @Min(value = 1, message = "Reservation quantity must be greater than 0")
    private Integer quantity;
}

