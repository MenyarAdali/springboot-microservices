package org.ms.authentification_service;

import org.ms.authentification_service.entities.AppUser;
import org.ms.authentification_service.services.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class AuthentificationServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(AuthentificationServiceApplication.class, args);
    }
    
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }


    @Bean
    CommandLineRunner start(UserService userService) {
        return args -> {
            userService.addUser(new AppUser(null, "user1", "123", null));
            userService.addUser(new AppUser(null, "user2", "456", null));
            userService.addRole(new org.ms.authentification_service.entities.AppRole(null, "USER"));
            userService.addRole(new org.ms.authentification_service.entities.AppRole(null, "ADMIN"));
            userService.addRoleToUser("user1", "USER");
            userService.addRoleToUser("user2", "USER");
            userService.addRoleToUser("user2", "ADMIN");
        };
    }
}