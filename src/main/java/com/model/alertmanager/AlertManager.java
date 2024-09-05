package com.model.alertmanager;

import com.model.alert.Alert;

import java.util.ArrayList;


public abstract class AlertManager {
    protected ArrayList<Alert> storedAlerts;

    public AlertManager() {
        this.storedAlerts = new ArrayList<>();
    }

    public ArrayList<Alert> getAllAlerts() {
        return new ArrayList<>(this.storedAlerts);
    }

    public abstract ArrayList<Alert> getAllValidAlerts();

    public void storeAlert(Alert alert) {
        alert.appendAlert(storedAlerts);
    }
}
