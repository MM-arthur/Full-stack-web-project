package com.example.restful_webservice.controller;

import com.example.restful_webservice.model.User;
import com.example.restful_webservice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable Long id) {
        return userService.getUserById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public User createUser(@RequestBody User user) {
        return userService.saveUser(user);
    }

    @PutMapping("/{id}")
    public ResponseEntity<User> updateUser(@PathVariable Long id, @RequestBody User user) {
        return userService.getUserById(id)
                .map(existingUser -> {
                    existingUser.setUserName(user.getUserName());
                    return ResponseEntity.ok(userService.saveUser(existingUser));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    // @DeleteMapping("/{id}")
    // public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
    // }
}
