package com.example.authservice.controller;

import com.example.authservice.dto.UserDto;
import com.example.authservice.model.User;
import com.example.authservice.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RequestMapping("/auth/api/user")
@RestController
public class UserController {

    private final UserService userService;
    public UserController(UserService userService){
        this.userService = userService;
    }

    @PostMapping("/save")
    public ResponseEntity<UserDto> saveUser(@RequestBody User user){
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(userService.saveUser(user));
    }

    @DeleteMapping("/delete/{userName}")
    public void deleteUser(@PathVariable String userName){
        userService.deleteUser(userName);
    }


    @GetMapping("/get/{userName}")
    public UserDto getUser(@PathVariable String userName) {
         return userService.getUser(userName);
    }







}
