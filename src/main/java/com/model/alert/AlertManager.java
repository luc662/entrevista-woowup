package com.model.alert;

import java.util.ArrayList;

public class AlertManager {
    private ArrayList<Alert> storedAlerts;


    public AlertManager() {
        this.storedAlerts = new ArrayList<>();
    }

    public ArrayList<Alert> getAllAlerts() {
        return new ArrayList<>(this.storedAlerts);
    }

    public void storeAlert(Alert alert) {
        alert.apendAlert(storedAlerts);
    }
}
