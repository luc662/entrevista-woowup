package com.model.topic;

import com.exceptions.NonexistentUserException;
import com.exceptions.UserAlreadyRegistredException;
import com.model.User.User;
import com.model.alert.Alert;
import com.model.alertmanager.AlertManager;
import com.model.alertmanager.TopicAlertManager;

import java.util.ArrayList;
import java.util.HashMap;

public class Topic {
    private HashMap<String, User> registeredUsers;
    private AlertManager alertManager;

    public Topic() {
        this.registeredUsers = new HashMap<>();
        this.alertManager = new TopicAlertManager();
    }

    public void registerUser(User user) {
        if (this.registeredUsers.containsKey(user.getName())) {
            throw new UserAlreadyRegistredException();
        }

        this.registeredUsers.put(user.getName(), user);
    }

    public void receiveAlert(Alert alert) {
        alertManager.storeAlert(alert);
        registeredUsers.values().forEach(user -> user.receiveAlert(alert.copy()));
    }

    public void receiveAlert(Alert alert, String userName) {
        User user = registeredUsers.get(userName);
        if (user == null) {
            throw new NonexistentUserException();
        }
        alert.markForSpecificUser();
        user.receiveAlert(alert);
        alertManager.storeAlert(alert);
    }

    public ArrayList<Alert> getAllNonExpiredAlerts() {
        return alertManager.getAllValidAlerts();
    }
}
