package com.model.alertmanager;

import com.model.alert.Alert;

import java.util.ArrayList;
import java.util.stream.Collectors;

public class TopicAlertManager extends AlertManager {

    public ArrayList<Alert> getAllValidAlerts() {
        return storedAlerts.stream()
                .filter(alert -> !alert.isExpired())
                .collect(Collectors.toCollection(ArrayList::new));
    }

}
