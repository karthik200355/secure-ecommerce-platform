
package com.ecommerce.cart_service.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Column;

// Request data ni validate cheyadaniki ee annotations use chestham
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

// Lombok annotations: getters, setters, no-argument constructor generate chesthayi
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "cart_item")
public class CartItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // Prathi cart item ki unique ID

    @NotNull(message = "Product ID is required")
    @Column(nullable = false)
    private Long productId; // Cart lo add chesthunna product ID

    @NotNull(message = "User ID is required")
    @Column(nullable = false)
    private Long userId; // Ee cart e user ki belong avuthundo

    @NotNull(message = "Quantity is required")
    @Min(value = 1, message = "Quantity must be at least 1")
    @Column(nullable = false)
    private Integer quantity; // Quantity minimum 1 undali

    @NotNull(message = "Price is required")
    @DecimalMin(value = "0.0", inclusive = false,
            message = "Price must be greater than 0")
    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal price; // Product price ni accurate decimal format lo store chesthundi
}
