package com.example.authservice;

import com.example.authservice.model.Role;
import com.example.authservice.model.User;
import com.example.authservice.service.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class AuthServiceApplication implements CommandLineRunner {

    private final UserService userService;

    public AuthServiceApplication(UserService userService) {
        this.userService = userService;
    }

    public static void main(String[] args) {
        SpringApplication.run(AuthServiceApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        User user = new User();
        user.setUserName("ernn");
        user.setPassword("12345");
        user.setRole(Role.ADMIN);
        user.setEmail("ernn.@hotmail.com");

        //userService.saveUser(user);

    }
}
