package com.model.User;


import com.model.alert.Alert;
import com.model.alertmanager.AlertManager;
import com.model.alertmanager.UserAlertManager;

import java.util.ArrayList;

public class User {
    private String name;
    private AlertManager alertManager;

    public User(String name) {
        this.name = name;
        this.alertManager = new UserAlertManager();
    }

    public String getName() {
        return name;
    }

    public void receiveAlert(Alert alert) {
        this.alertManager.storeAlert(alert);
    }

    public ArrayList<Alert> getAllValidAlerts() {
        return new ArrayList<>(this.alertManager.getAllValidAlerts());
    }
}
