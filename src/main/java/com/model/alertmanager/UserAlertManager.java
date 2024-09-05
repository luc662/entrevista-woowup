package com.model.alertmanager;

import com.model.alert.Alert;

import java.util.ArrayList;
import java.util.stream.Collectors;

public class UserAlertManager extends AlertManager {

    public ArrayList<Alert> getAllValidAlerts() {
        return storedAlerts.stream()
                .filter(alert -> !alert.isExpired() && !alert.isRead())
                .collect(Collectors.toCollection(ArrayList::new));
    }

}
