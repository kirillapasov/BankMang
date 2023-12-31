package com.example.BankMang.repository;


import com.example.BankMang.model.User;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

@Repository
public class UserDao {
    private static final List<User> users = new ArrayList<>();
    private static final AtomicLong userIdCounter = new AtomicLong(1);

    public Long createUser(User user) {
        Long userId = userIdCounter.getAndIncrement();
        user.setId(userId);
        users.add(user);
        return userId;
    }

    public User getUserById(Long userId) {
        return users.stream()
                .filter(u -> u.getId().equals(userId))
                .findFirst()
                .orElse(null);
    }

    public List<User> getAllUsers() {
        return new ArrayList<>(users);
    }

    public User updateUser(Long userId, User updatedUser) {
        for (int i = 0; i < users.size(); i++) {
            if (users.get(i).getId().equals(userId)) {
                users.set(i, updatedUser);
                return updatedUser;
            }
        }
        return null;
    }

    public void deleteUser(Long userId) {
        users.removeIf(user -> user.getId().equals(userId));
    }
}
