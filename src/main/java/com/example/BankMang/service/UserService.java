package com.example.BankMang.service;

import com.example.BankMang.model.User;
import org.springframework.stereotype.Service;
import com.example.BankMang.repository.UserDao;

import java.util.List;

@Service
public class UserService {
    private final UserDao userDao;

    public UserService(UserDao userDao) {
        this.userDao = userDao;
    }

    public Long createUser(User user) {
        return userDao.createUser(user);
    }

    public User getUserById(Long userId) {
        return userDao.getUserById(userId);
    }

    public List<User> getAllUsers() {
        return userDao.getAllUsers();
    }

    public User updateUser(Long userId, User updatedUser) {
        return userDao.updateUser(userId, updatedUser);
    }

    public void deleteUser(Long userId) {
        userDao.deleteUser(userId);

    }
}