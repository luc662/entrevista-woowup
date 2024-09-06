package com.model.User;

import com.exceptions.NonexistentUserException;
import com.exceptions.UserAlreadyRegistredException;

import java.util.HashMap;

public class UserRegister {
    private HashMap<String, User> users;

    public UserRegister() {
        this.users = new HashMap<>();
    }

    public void registerUser(String userName) {
        if (this.users.containsKey(userName)) {
            throw new UserAlreadyRegistredException();
        }
        User user = new User(userName);
        this.users.put(userName, user);
    }

    public User getUserByName(String userName) {
        if (!this.users.containsKey(userName)) {
            throw new NonexistentUserException();
        }
        return this.users.get(userName);
    }
}
