package com.main.model.topic;

import com.main.exceptions.UserAlreadyRegistredException;
import com.main.model.User.User;
import com.main.model.alert.Alert;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;

public class Topic {
    private HashMap<String, User> registeredUsers;
    private ArrayList<Alert> alerts;

    public Topic() {
        this.registeredUsers = new HashMap<>();
        this.alerts = new ArrayList<>();
    }

    public void registerUser(User user) {
        if (this.registeredUsers.containsKey(user.getName())) {
            throw new UserAlreadyRegistredException();
        }

        this.registeredUsers.put(user.getName(), user);
    }

    public void postAlert(Alert alert) {
        alert.apendMessage(alerts);
        registeredUsers.values().forEach(user -> user.receiveAlert(alert));
    }

    public Collection<User> getAllUsers() {
        return registeredUsers.values();
    }

    public ArrayList<Alert> getAllAlerts() {
        return new ArrayList<>(this.alerts);
    }
}
