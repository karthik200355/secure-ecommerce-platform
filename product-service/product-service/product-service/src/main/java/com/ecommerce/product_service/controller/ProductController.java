
package com.ecommerce.product_service.controller; // Defines the package where this controller belongs.

import com.ecommerce.product_service.entity.Product; // Imports the Product entity used to represent product data.
import com.ecommerce.product_service.service.ProductService; // Imports the service that contains product business logic.

import org.springframework.web.bind.annotation.*; // Imports REST annotations such as @RestController, @GetMapping, and @RequestBody.
import org.springframework.http.ResponseEntity; // Allows us to control the HTTP response and status code.
import org.springframework.http.HttpStatus; // Provides standard HTTP status codes such as 201 Created.

import java.util.List; // Imports List to return multiple products.

// Tells Spring Boot that this class handles HTTP requests and returns response data.
@RestController

// Adds /api/products as the common URL prefix for all endpoints in this controller.
@RequestMapping("/api/products")
public class ProductController { // Declares the controller class.

    // Stores the ProductService reference used to perform product operations.
    private final ProductService productService;

    // Constructor injection lets Spring provide the ProductService object automatically.
    public ProductController(ProductService productService) {
        this.productService = productService; // Saves the injected service for use in endpoint methods.
    }

    // Handles GET /api/products requests to retrieve all products.
    @GetMapping
    public List<Product> getAllProducts() { // Returns a list of Product objects as JSON.
        return productService.getAllProducts(); // Calls the service to fetch all products from the database.
    }

    // Handles GET /api/products/{id}, where {id} represents a product ID.
    @GetMapping("/{id}")
    public Product getProductById(@PathVariable Long id) { // Reads the ID value from the URL.
        return productService.getProductById(id); // Calls the service to find the product with that ID.
    }

    // Handles POST /api/products requests to create a new product.
    @PostMapping
    public ResponseEntity<Product> createProduct(@RequestBody Product product) { // Converts the incoming JSON request body into a Product object.
        Product savedProduct = productService.createProduct(product); // Asks the service to save the product and stores the saved result.

        // Returns HTTP 201 Created along with the newly saved product in the response body.
        return ResponseEntity.status(HttpStatus.CREATED).body(savedProduct);
    }

    // Handles PUT /api/products/{id} requests to update an existing product.
    @PutMapping("/{id}")
    public Product updateProduct( // Declares the method that updates and returns a product.
            @PathVariable Long id, // Reads the ID of the product to update from the URL.
            @RequestBody Product product) { // Reads the new product details from the JSON request body.
        return productService.updateProduct(id, product); // Calls the service to update the matching product.
    }

    // Handles DELETE /api/products/{id} requests to delete a product.
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long id) { // Reads the ID and declares a response without a body.
        productService.deleteProduct(id); // Calls the service to delete the product with the specified ID.

        // Returns HTTP 204 No Content to indicate successful deletion without response data.
        return ResponseEntity.noContent().build();
    }
}
