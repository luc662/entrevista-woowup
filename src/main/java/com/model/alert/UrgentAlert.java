package com.model.alert;

import java.time.Instant;
import java.util.ArrayList;

public class UrgentAlert extends Alert{
    public UrgentAlert(String title, String body) {
        super(title, body);
    }

    public UrgentAlert(String title, String body, Instant expirationTime) {
        super(title, body, expirationTime);
    }

    @Override
    public void apendAlert(ArrayList<Alert> alerts) {
        alerts.add(0,this);
    }
}
