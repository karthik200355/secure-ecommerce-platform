
package com.ecommerce.auth_service.repository;

// Imports the User entity that represents the users database table.
import com.ecommerce.auth_service.entity.User;

// Provides database operations such as save(), findAll(), and findById().
import org.springframework.data.jpa.repository.JpaRepository;

// Marks this interface as the repository for User records.
public interface UserRepository extends JpaRepository<User, Long> {

    // Finds a user by email; Spring Data JPA generates the query automatically.
    User findByEmail(String email);

    // Returns true if the email already exists; otherwise returns false.
    boolean existsByEmail(String email);
}