package com.sofkaU.postAPP.controller;

import com.sofkaU.postAPP.entity.User;
import com.sofkaU.postAPP.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/v1/")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("save/user")
    public ResponseEntity<String> createUser(@RequestBody User user) {
        return userService.createUser(user);
    }

    @GetMapping("get/users")
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("get/user/id/{userId}")
    public Optional<User> getUserById(@PathVariable("userId") Long userId) {
        return userService.getUserById(userId);
    }

    @GetMapping("get/user/email")
    public Optional<User> getUserByEmail(@RequestBody User user) {
        return userService.getUserByEmail(user);
    }

    @PutMapping("update/user")
    public ResponseEntity<String> updateUser(@RequestBody User user) {
        return userService.updateUser(user);
    }

    @DeleteMapping("delete/user/{userId}")
    public void deleteUser(@PathVariable("userId") Long userId) {
        userService.deleteUser(userId);
    }

}
