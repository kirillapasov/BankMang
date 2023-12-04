package repository;


import model.User;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

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

    public void updateUser(User updatedUser) {
        for (int i = 0; i < users.size(); i++) {
            if (users.get(i).getId().equals(updatedUser.getId())) {
                users.set(i, updatedUser);
                break;
            }
        }
    }

    public void deleteUser(Long userId) {
        users.removeIf(user -> user.getId().equals(userId));
    }
}
