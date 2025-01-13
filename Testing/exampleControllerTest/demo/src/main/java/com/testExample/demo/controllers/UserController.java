package com.testExample.demo.controllers;

import com.testExample.demo.models.Address;
import com.testExample.demo.models.User;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @GetMapping("/{id}")
    public ResponseEntity<User> getUser(@PathVariable int id) {
        Address address = new Address("123 Main St", "Springfield");
        User user = new User("John Doe", 30, address);
        return ResponseEntity.ok(user);
    }
}