
package com.ecommerce.auth_service.config;

// Marks this class as a source of Spring configuration.
import org.springframework.context.annotation.Configuration;

// Allows Spring to register methods as beans.
import org.springframework.context.annotation.Bean;

// Defines HTTP security rules.
import org.springframework.security.config.annotation.web.builders.HttpSecurity;

// Provides the security filter chain for incoming HTTP requests.
import org.springframework.security.web.SecurityFilterChain;

// Interface for password hashing.
import org.springframework.security.crypto.password.PasswordEncoder;

// BCrypt implementation for secure password hashing.
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

// Identifies this class as a configuration class.
@Configuration
public class SecurityConfig {

    // Configures which HTTP requests are allowed or protected.
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http)
            throws Exception {

        http
            // Allows registration requests without requiring login.
            .authorizeHttpRequests(auth -> auth
                .requestMatchers("/api/auth/register").permitAll()

                // Requires authentication for every other request.
                .anyRequest().authenticated()
            )

            // Disables CSRF protection for our initial REST API testing.
            // We will review security settings as the API develops.
            .csrf(csrf -> csrf.disable());

        // Returns the configured security rules to Spring.
        return http.build();
    }

    // Registers the password encoder used by AuthService.
    @Bean
    public PasswordEncoder passwordEncoder() {

        // BCrypt hashes passwords so plain-text passwords aren't stored.
        return new BCryptPasswordEncoder();
    }
}
