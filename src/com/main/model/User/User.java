package com.main.model.User;

import com.main.model.alert.Alert;
import com.main.model.alert.AlertManager;

import java.util.ArrayList;

public class User {
    private String name;
    private AlertManager alertManager;

    public User(String name) {
        this.name = name;
        this.alertManager = new AlertManager();
    }

    public String getName() {
        return name;
    }

    public void receiveAlert(Alert alert) {
        this.alertManager.storeAlert(alert);
    }

    public ArrayList<Alert> getAllAlerts() {
        return new ArrayList<>(this.alertManager.getAllAlerts());
    }
}
