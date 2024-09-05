package com.model.topic;

import com.exceptions.NonexistentUserError;
import com.model.User.User;
import com.model.alert.Alert;
import com.model.alert.AlertManager;

import com.exceptions.UserAlreadyRegistredException;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;

public class Topic {
    private HashMap<String, User> registeredUsers;
    private AlertManager alertManager;

    public Topic() {
        this.registeredUsers = new HashMap<>();
        this.alertManager = new AlertManager();
    }

    public void registerUser(User user) {
        if (this.registeredUsers.containsKey(user.getName())) {
            throw new UserAlreadyRegistredException();
        }

        this.registeredUsers.put(user.getName(), user);
    }

    public void receiveAlert(Alert alert) {
        alertManager.storeAlert(alert);
        registeredUsers.values().forEach(user -> user.receiveAlert(alert));
    }

    public void receiveAlert(Alert alert, String userName) {
        User user = registeredUsers.get(userName);
        if (user == null) {
            throw new NonexistentUserError();
        }
        user.receiveAlert(alert);
        alertManager.storeAlert(alert);
    }

    public Collection<User> getAllUsers() {
        return registeredUsers.values();
    }

    public ArrayList<Alert> getAllNonExpiredAlerts() {
        return alertManager.getAllNonExpiredAlerts();
    }
}
