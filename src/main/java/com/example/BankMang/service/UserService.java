package com.example.BankMang.service;

import model.User;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private final List<User> users = new ArrayList<>();

    public List<User> getAllUsers() {
        return users;
    }

    public Optional<User> getUserByEmail(String email) {
        return users.stream()
                .filter(user -> user.getEmail().equals(email))
                .findFirst();
    }

    public User createUser(User user) {
        // Дополнительная бизнес-логика, если необходимо
        users.add(user);
        return user;
    }

    public Optional<User> updateUser(String email, User updatedUser) {
        // Дополнительная бизнес-логика, если необходимо
        Optional<User> existingUser = getUserByEmail(email);
        existingUser.ifPresent(user -> {
            user.setFirstName(updatedUser.getFirstName());
            user.setSecondName(updatedUser.getSecondName());
            user.setPhoneNumber(updatedUser.getPhoneNumber());
            user.setPassportData(updatedUser.getPassportData());
            // Другие поля...
        });
        return existingUser;
    }

    public boolean deleteUser(String email) {
        // Дополнительная бизнес-логика, если необходимо
        Optional<User> userToRemove = getUserByEmail(email);
        if (userToRemove.isPresent()) {
            users.remove(userToRemove.get());
            return true;
        }
        return false;
    }
}
