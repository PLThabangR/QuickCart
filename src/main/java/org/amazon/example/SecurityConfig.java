package org.amazon.example;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.User.UserBuilder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    private final UserService userService;

    public SecurityConfig(UserService userService) {
        this.userService = userService;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers("/register", "/login").permitAll() // Allow public access
                        .requestMatchers("/admin/**").hasRole("ADMIN")     // TODO 3: Only ADMIN can access /admin/*
                        .requestMatchers("/user/**").hasRole("USER")       // TODO 4: Only USER can access /user/*
                        .anyRequest().authenticated()                      // Secure all other pages
                )
                .formLogin(login -> login
                        .loginPage("/login")
                        .defaultSuccessUrl("/welcome", true)
                        .permitAll()                                       // Enable form login
                )
                .logout(logout -> logout.permitAll());                 // Optional: allow logout for all

        return http.build();
    }

    @Bean
    public UserDetailsService userDetailsService() {
        return username -> {
            org.amazon.example.User user = userService.findByUsername(username);
            if (user == null) {
                throw new UsernameNotFoundException("User not found");
            }
            UserBuilder builder = User.withUsername(user.getUsername());
            builder.password(user.getPassword());  // Password is already encoded
            if(user.getRole() != null) {
                builder.roles(user.getRole());
            }
            return builder.build();
        };
    }
}
