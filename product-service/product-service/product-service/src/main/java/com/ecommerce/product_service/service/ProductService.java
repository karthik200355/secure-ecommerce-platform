package com.ecommerce.product_service.service;

import com.ecommerce.product_service.entity.Product;

import com.ecommerce.product_service.repository.ProductRepository;

import org.springframework.stereotype.Service;

import com.ecommerce.product_service.exception.ProductNotFoundException; // Imports our custom exception for missing products.


import java.util.List;

@Service 
public class ProductService {
    
    // Repository handles communication with the database.
    private final ProductRepository productRepository;

     // Constructor injection gives this service its repository.
    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

     // Retrieves all products from the database.
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }
        
    
  // Retrieves one product using its ID.
public Product getProductById(Long id) {

    // Finds the product or throws a custom exception if it does not exist.
    return productRepository.findById(id)
            .orElseThrow(() ->
                    new ProductNotFoundException(
                            "Product not found with ID: " + id));
}

    
    // Saves a new product in the database.
    public Product createProduct(Product product) {

        // Repository saves the product and returns the saved record.
        return productRepository.save(product);
    }

    // Updates an existing product using its ID.
    public Product updateProduct(Long id, Product updatedProduct) {

        // Find the existing product; throw an error if the ID does not exist.
        Product existingProduct = getProductById(id);

        // Update the product details with the new values.
        existingProduct.setName(updatedProduct.getName());
        existingProduct.setDescription(updatedProduct.getDescription());
        existingProduct.setPrice(updatedProduct.getPrice());

        // Save the updated product to the database.
        return productRepository.save(existingProduct);
    }

    // Deletes a product using its ID.
    public void deleteProduct(Long id) {

        // First, check whether the product exists.
        getProductById(id);

        // Delete the product from the database.
        productRepository.deleteById(id);
    }


}
