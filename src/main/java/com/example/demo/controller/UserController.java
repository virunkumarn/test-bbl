package com.example.demo.controller;

import com.example.demo.model.UserModel;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("users")
public class UserController {

    private List<UserModel> users;

    public UserController() {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            users = objectMapper.readValue(
                    getClass().getResourceAsStream("/user.json"),
                    objectMapper.getTypeFactory().constructCollectionType(List.class, UserModel.class)
            );
        } catch (Exception e) {
            throw new RuntimeException("Failed to load users data", e);
        }
    }

    @GetMapping()
    public List<UserModel> getUsers() {
        return users;
    }

    @GetMapping("/{userId}")
    public ResponseEntity<UserModel> getUserById(@PathVariable int userId) {
        for (UserModel user : users) {
            if (user.getId() == userId) {
                return ResponseEntity.ok(user);
            }
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping()
    public ResponseEntity<UserModel> createUser(@Valid @RequestBody UserModel userModel) {
        userModel.setId(users.size() + 1); // Simple ID generation
        users.add(userModel);
        return ResponseEntity.status(HttpStatus.CREATED).body(userModel);
    }

    @PutMapping("/{userId}")
    public ResponseEntity<UserModel> updateUser(@PathVariable int userId, @Valid @RequestBody UserModel userModel) {

        for (int i = 0; i < users.size(); i++) {
            if (users.get(i).getId() == userId) {
                users.set(i, userModel);
                users.get(i).setId(userId);
                return ResponseEntity.ok(userModel);
            }
        }
        return ResponseEntity.notFound().build();

    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<UserModel> deleteUser(@PathVariable int userId) {
        for (int i = 0; i < users.size(); i++) {
            if (users.get(i).getId() == userId) {
                UserModel removedUser = users.remove(i);
                return ResponseEntity.ok(removedUser);
            }
        }
        return ResponseEntity.notFound().build();

    }


}
