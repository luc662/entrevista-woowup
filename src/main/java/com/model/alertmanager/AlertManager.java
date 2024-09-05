package com.model.alertmanager;

import com.model.alert.Alert;

import java.util.ArrayList;
import java.util.stream.Collectors;

public class AlertManager {
    private ArrayList<Alert> storedAlerts;


    public AlertManager() {
        this.storedAlerts = new ArrayList<>();
    }

    public ArrayList<Alert> getAllAlerts() {
        return new ArrayList<>(this.storedAlerts);
    }

    public ArrayList<Alert> getAllNonExpiredAlerts() {
        return storedAlerts.stream()
                .filter(alert -> !alert.isExpired())
                .collect(Collectors.toCollection(ArrayList::new));
    }
    public ArrayList<Alert> getAllNonReadAndNonExpiredAlerts() {
        return storedAlerts.stream()
                .filter(alert -> !alert.isExpired() && !alert.isRead())
                .collect(Collectors.toCollection(ArrayList::new));
    }

    public void storeAlert(Alert alert) {
        alert.appendAlert(storedAlerts);
    }
}
