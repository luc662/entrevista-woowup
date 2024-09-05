package com.model.User;

import com.exceptions.UserAlreadyRegistredException;

import java.util.Collection;
import java.util.HashMap;

public class UserRegister {
    private HashMap<String, User> users;

    public UserRegister() {
        this.users = new HashMap<>();
    }

    public void registerUser(User user) {
        if (this.users.containsKey(user.getName())) {
            throw new UserAlreadyRegistredException();
        }

        this.users.put(user.getName(), user);
    }

    public Collection<User> getAllUsers() {
        return users.values();
    }
}
