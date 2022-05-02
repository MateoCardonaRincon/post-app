package com.sofkaU.postAPP.service;

import com.sofkaU.postAPP.entity.User;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

public interface UserServiceInterface {

    ResponseEntity<String> createUser(User user);

    Optional<User> getUserById(Long userId);

    Optional<User> getUserByEmail(User user);

    List<User> getAllUsers();

    ResponseEntity<String> updateUser(User user);

    void deleteUser(Long userId);
}
