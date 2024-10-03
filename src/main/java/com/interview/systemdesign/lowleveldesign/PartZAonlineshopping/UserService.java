package com.interview.systemdesign.lowleveldesign.PartZAonlineshopping;

import java.util.HashMap;
import java.util.Map;

public class UserService {
    private Map<String, User> users = new HashMap<>();

    public void registerUser(User user) {
        users.put(user.getEmail(), user);
    }

    public User loginUser(String email, String password) {
        User user = users.get(email);
        return (user != null && user.getPassword().equals(password)) ? user : null;
    }

    public void updateUserProfile(User user) {
        users.put(user.getEmail(), user);
    }
}

