package com.main.model.topic;

import com.main.exceptions.NonexistentUserError;
import com.main.exceptions.UserAlreadyRegistredException;
import com.main.model.User.User;
import com.main.model.alert.Alert;
import com.main.model.alert.AlertManager;

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
        if(user == null) {
            throw new NonexistentUserError();
        }
        user.receiveAlert(alert);
        alertManager.storeAlert(alert);
    }

    public Collection<User> getAllUsers() {
        return registeredUsers.values();
    }

    public ArrayList<Alert> getAllAlerts() {
        return alertManager.getAllAlerts();
    }
}
