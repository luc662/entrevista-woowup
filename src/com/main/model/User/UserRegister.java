package com.main.model.User;


import java.util.Collection;
import java.util.HashMap;


public class UserRegister {
    private HashMap<String, User> registredUsers;

    public UserRegister() {
        this.registredUsers = new HashMap<String, User>();
    }

    public void registerUser(User user){
        this.registredUsers.put(user.getName(),user);
    }

    public Collection<User> getAllUsers() {
        return registredUsers.values();
    }
}
